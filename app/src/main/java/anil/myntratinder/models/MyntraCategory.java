package anil.myntratinder.models;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import anil.myntratinder.R;

/**
 * Created by Anil on 8/24/2014.
 */
public class MyntraCategory {

    public static class ProductGroup{

        private String groupLabel;
        private String fileName;
        private String startFromKey;
        private String maxProductsKey;
        private String postDataHead;
        private String postDataTail;

        public ProductGroup(String groupLabel, String fileName, String startFromKey, String maxProductsKey, String postDataHead, String postDataTail) {
            this.groupLabel = groupLabel;
            this.fileName = fileName;
            this.startFromKey = startFromKey;
            this.maxProductsKey = maxProductsKey;
            this.postDataHead = postDataHead;
            this.postDataTail = postDataTail;
        }

        public String getGroupLabel() {
            return groupLabel;
        }

        public void setGroupLabel(String groupLabel) {
            this.groupLabel = groupLabel;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getStartFromKey() {
            return startFromKey;
        }

        public void setStartFromKey(String startFromKey) {
            this.startFromKey = startFromKey;
        }

        public String getMaxProductsKey() {
            return maxProductsKey;
        }

        public void setMaxProductsKey(String maxProductsKey) {
            this.maxProductsKey = maxProductsKey;
        }

        public String getPostDataHead() {
            return postDataHead;
        }

        public void setPostDataHead(String postDataHead) {
            this.postDataHead = postDataHead;
        }

        public String getPostDataTail() {
            return postDataTail;
        }

        public void setPostDataTail(String postDataTail) {
            this.postDataTail = postDataTail;
        }
    }
    public static class ProductHeadGroup{

        private String groupName;
        private List<ProductGroup> childGroups;

        public ProductHeadGroup(String groupName, List<ProductGroup> childGroups) {
            this.groupName = groupName;
            this.childGroups = childGroups;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public List<ProductGroup> getChildGroups() {
            return childGroups;
        }

        public void setChildGroups(List<ProductGroup> childGroups) {
            this.childGroups = childGroups;
        }
    }

    public static List<ProductHeadGroup> generateSampleProductHeadGroups(Context context) {
        List<ProductHeadGroup> productHeadGroups = new ArrayList<ProductHeadGroup>();
        List<ProductGroup> productGroups = new ArrayList<ProductGroup>();

        ProductGroup productGroup = new ProductGroup(
                context.getString(R.string.men_shoes_group_label),
                context.getString(R.string.men_shoes_filename),
                context.getString(R.string.men_shoes_start_from_key),
                context.getString(R.string.men_shoes_max_products_key),
                context.getString(R.string.men_casual_shirts_post_data_head),
                context.getString(R.string.men_shoes_post_data_tail)
        );

        productGroups.add(productGroup);
        productGroups.add(productGroup);
        productGroups.add(productGroup);
        productHeadGroups.add(new ProductHeadGroup("Men", productGroups));
        productHeadGroups.add(new ProductHeadGroup("Women", productGroups));
        productHeadGroups.add(new ProductHeadGroup("Kids", productGroups));

        return productHeadGroups;
    }
}
