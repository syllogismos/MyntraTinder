package anil.myntratinder.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.renderscript.Sampler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import anil.myntratinder.R;

/**
 * Created by Anil on 7/18/2014.
 */
public class ProductStackView extends RelativeLayout{

    public interface ProductStackListener{
        void onUpdateProgress(boolean positif, float percent, View view);
        void onCancelled(View beingDragged);
        void onChoiceMade(boolean choice, View beingDragged);
    }

    private static int STACK_SIZE = 4;
    private static int MAX_ANGLE_DEGREE = 20;
    private BaseAdapter mAdapter;
    private int mCurrentPosition;
    private int mMinDragDistance;
    private int mMinAcceptDistance;

    private int mXDelta;
    private int mYDelta;

    protected LinkedList<View> mProducts = new LinkedList<View>();
    protected LinkedList<View> mRecycledProducts = new LinkedList<View>();

    private ProductStackListener mProductStackListener;

    protected LinkedList<Object> mProductStack = new LinkedList<Object>();
    private int mXStart;
    private int mYStart;

    private View mBeingDragged;
    private MyOnTouchListener mMyOnTouchListener;


    public ProductStackView(Context context) {
        super(context);
        setup();
    }

    public ProductStackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public ProductStackView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mBeingDragged != null){
            mXDelta = (int) mBeingDragged.getTranslationX();
            mYDelta = (int) mBeingDragged.getTranslationY();
        }

        int index = 0;
        Iterator<View> it = mProducts.descendingIterator();
        while (it.hasNext()){
            View card = it.next();
            if (card == null)
                break;

            if (isTopCard(card)){
                card.setOnTouchListener(mMyOnTouchListener);
            } else {
                card.setOnTouchListener(null);
            }

            if (index == 0 && adapterHasMoreItems()) {
                if (mBeingDragged != null){
                    index++;
                    continue;
                }

                scaleAndTranslate(1, card);
            } else {
                scaleAndTranslate(index, card);
            }

            index++;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public ProductStackListener getmProductStackListener() {
        return mProductStackListener;
    }

    public void setmProductStackListener(ProductStackListener mProductStackListener) {
        this.mProductStackListener = mProductStackListener;
    }

    private void scaleAndTranslate(int cardIndex, View view) {
        LinearInterpolator interpolator = new LinearInterpolator();

        if (view == mBeingDragged){
            int sign = 1;
            if (mXDelta > 0){
                sign = -1;
            }
            float progress = Math.min(Math.abs(mXDelta)/((float)mMinAcceptDistance*5), 1);
            float angleDegree = MAX_ANGLE_DEGREE * interpolator.getInterpolation(progress);

            view.setRotation(sign*angleDegree);

            return;
        }

        float zoomFactor = 0;
        if (mBeingDragged != null){
            float interpolation = 0;
            float distance = (float) Math.sqrt(mXDelta*mXDelta + mYDelta*mYDelta);
            float progress = Math.min(distance/mMinDragDistance, 1);
            interpolation = interpolator.getInterpolation(progress);
            interpolation = Math.min(interpolation, 1);
            zoomFactor = interpolation;
        }

        int position = STACK_SIZE - cardIndex;
        float step = 0.025f;

        Resources r = getContext().getResources();
        float translateStep = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                10, r.getDisplayMetrics());
        float scale = step * (position - zoomFactor);
        float translate = translateStep * (position - zoomFactor);
        view.setTranslationY(translate);
        view.setTranslationX(0);
        view.setRotation(0);
        view.setScaleY(1-scale);
        view.setScaleX(1-scale);

        return;

    }



    private boolean adapterHasMoreItems() {
        return mCurrentPosition < mAdapter.getCount() - 1;
    }

    private boolean isTopCard(View card) {
        return card == mProducts.peek();
    }

    private void setup() {
        Resources r = getContext().getResources();
        mMinDragDistance = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                50, r.getDisplayMetrics());
        mMinAcceptDistance = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                40, r.getDisplayMetrics());

        if (isInEditMode()){
            mAdapter = new MockListAdapter(getContext());
        }

        mCurrentPosition = 0;
    }

    public void setAdapter(BaseAdapter adapter){
        mAdapter = adapter;
        mRecycledProducts.clear();
        mProducts.clear();
        removeAllViews();
        mCurrentPosition = 0;
        initializeStack();
    }

    private void initializeStack() {
        int position = 0;
        // fixme: possible fix for the unusual error of the 4th product repeating, mCurrentPosition + STACK_SIZE -1 or
        // fixme: mCurrentPosition += position instead of position - 1, in the end of this function
        for (; position < mCurrentPosition + STACK_SIZE; position++){
            if (position >= mAdapter.getCount())
                break;

            Object item = mAdapter.getItem(position);
            mProductStack.offer(item);

            View product = mAdapter.getView(position, null, null);
            mProducts.offer(product);

            RelativeLayout.LayoutParams params = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            addView(product, 0, params);

            mMyOnTouchListener = new MyOnTouchListener();
        }
        mCurrentPosition += position - 1;
    }

    private class MyOnTouchListener implements OnTouchListener{
        @Override
        public boolean onTouch(final View view, MotionEvent motionEvent) {
            if (!isTopCard(view)){
                return false;
            }

            final int X = (int) motionEvent.getRawX();
            final int Y = (int) motionEvent.getRawY();

            final int action = motionEvent.getAction();
            switch (action & MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    mXStart = X;
                    mYStart = Y;
                    break;
                case MotionEvent.ACTION_UP:
                    if (mBeingDragged == null){
                        return false;
                    }
                    if (!canAcceptChoice()){
                        requestLayout();
                        AnimatorSet set = new AnimatorSet();

                        ObjectAnimator yTranslation = ObjectAnimator.ofFloat(mBeingDragged, "translationY", 0);
                        ObjectAnimator xTranslation = ObjectAnimator.ofFloat(mBeingDragged, "translationX", 0);
                        set.playTogether(xTranslation, yTranslation);

                        set.setDuration(100).start();
                        set.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                View finalView = mBeingDragged;
                                mBeingDragged = null;
                                mXDelta = 0;
                                mYDelta = 0;
                                mXStart = 0;
                                mYStart = 0;
                                requestLayout();
                                if (mProductStackListener != null){
                                    mProductStackListener.onCancelled(finalView);
                                }
                            }
                        });

                        ValueAnimator.AnimatorUpdateListener onUpdate = new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                mXDelta = (int) view.getTranslationX();
                                mYDelta = (int) view.getTranslationY();
                                requestLayout();
                            }
                        };
                        yTranslation.addUpdateListener(onUpdate);
                        xTranslation.addUpdateListener(onUpdate);
                        set.start();
                    }else{
                        final View last = mProducts.poll();
                        View recycled = getRecycledOrNew();
                        if (recycled != null){
                            RelativeLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.addRule(RelativeLayout.CENTER_IN_PARENT);

                            mProducts.offer(recycled);
                            addView(recycled, 0, params);
                        }

                        int sign = mXDelta > 0 ? +1 : -1;
                        mBeingDragged = null;
                        mXDelta = 0;
                        mYDelta = 0;
                        mXStart = 0;
                        mYStart = 0;

                        ObjectAnimator animation = ObjectAnimator.ofFloat(last, "translationX", sign*1000)
                                .setDuration(300);
                        animation.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (mProductStackListener != null){
                                    boolean choice = getStackChoice();
                                    mProductStackListener.onChoiceMade(choice, last);
                                }

                                recycleView(last);

                                final ViewGroup parent = (ViewGroup) view.getParent();
                                if (null != parent) {
                                    parent.removeView(view);
                                    parent.addView(view, 0);
                                }

                                last.setScaleX(1);
                                last.setScaleY(1);
                                setTranslationY(0);
                                setTranslationX(0);
                                requestLayout();
                            }
                        });
                        animation.start();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    boolean choiceBoolean = getStackChoice();
                    float progress = getStackProgress();

                    view.setTranslationX(X - mXStart);
                    view.setTranslationY(Y - mYStart);

                    mXDelta = X - mXStart;
                    mYDelta = Y - mYStart;

                    mBeingDragged = view;
                    requestLayout();

                    if (mProductStackListener != null){
                        mProductStackListener.onUpdateProgress(choiceBoolean, progress, mBeingDragged);
                    }

                    break;
            }
            return true;
        }
    }

    private float getStackProgress() {
        LinearInterpolator interpolator = new LinearInterpolator();
        float progress = Math.min(Math.abs(mXDelta)/((float)mMinAcceptDistance*5), 1);
        progress = interpolator.getInterpolation(progress);
        return progress;
    }

    private void recycleView(View last) {
        ((ViewGroup)last.getParent()).removeView(last);
        mRecycledProducts.offer(last);
    }

    private View getRecycledOrNew() {
        if (adapterHasMoreItems()){
            View view = mRecycledProducts.poll();
            view = mAdapter.getView(mCurrentPosition++, view, null);

            return view;
        } else {
            return null;
        }
    }

    private boolean canAcceptChoice() {
        return Math.abs(mXDelta) > mMinAcceptDistance;
    }

    private boolean getStackChoice() {
        return mXDelta > 0;
    }

    private class MockListAdapter extends BaseAdapter {
        List<String> mItems;
        Context mContext;

        public MockListAdapter(Context context) {
            mContext = context;
            mItems = new ArrayList<String>();
            for (int i = 1; i< 15; i++){
                mItems.add(i + "");
            }
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int i) {
            return mItems.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView view = new ImageView(mContext);
            view.setImageResource(R.drawable.sample_product_image);
            Resources r = mContext.getResources();
            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300,
                    r.getDisplayMetrics());
            LayoutParams params = new LayoutParams(px, px);
            view.setLayoutParams(params);
            return view;
        }
    }
}
