package anil.myntratinder.models;

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
        private String uniqueGroupLabel;
        private String fileName;
        private String startFromKey;
        private String maxProductsKey;
        private String postDataHead;
        private String postDataTail;

        public ProductGroup(String groupLabel, String uniqueGroupLabel, String fileName, String startFromKey, String maxProductsKey, String postDataHead, String postDataTail) {
            this.groupLabel = groupLabel;
            this.uniqueGroupLabel = uniqueGroupLabel;
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

        public String getUniqueGroupLabel() {
            return uniqueGroupLabel;
        }

        public void setUniqueGroupLabel(String uniqueGroupLabel) {
            this.uniqueGroupLabel = uniqueGroupLabel;
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
                "Casual Shoes",
                context.getString(R.string.men_shoes_group_label),
                context.getString(R.string.men_shoes_filename),
                context.getString(R.string.men_shoes_start_from_key),
                context.getString(R.string.men_shoes_max_products_key),
                context.getString(R.string.men_shoes_post_data_head),
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

    public static List<ProductHeadGroup> generateMyntraMenProductGroups(Context context){
        List<ProductHeadGroup> productHeadGroups = new ArrayList<ProductHeadGroup>();
        List<ProductGroup> productGroups;
        ProductGroup productGroup;

        /////////////////////   Men  /////////////////////////
        ///////////////////// Footwear ////////////////////
        productGroups = new ArrayList<ProductGroup>();
        // Casual Shoes //
        productGroup = new ProductGroup(
                "Casual Shoes", // group label
                "men-footwear-casual-shoes", // unique group label
                "men-footwear-casual-shoes-file", // file name
                "men-footwear-casual-shoes-start-key", // unique start from key
                "men-footwear-casual-shoes-max-products-key", // unique max products key
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Casual Shoes\\\") AND global_attr_master_category:(\\\"Footwear\\\" OR \\\"Free Items\\\"))\",\"start\":", // post data head
                ",\"rows\":96,\"facetField\":[\"Casual_Shoe_Type_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Ankle_Height_article_attr\",\"Width_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"  // post data tail
        );
        productGroups.add(productGroup);
        // Sport Shoes //
        productGroup = new ProductGroup(
                "Sport Shoes",
                "men-footwear-sport-shoes",
                "men-footwear-sport-shoes-file",
                "men-footwear-sport-shoes-start-key",
                "men-footwear-sport-shoes-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Sports Shoes\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Sport_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Outsole_article_attr\",\"Cleats_article_attr\",\"Width_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Formal Shoes //
        productGroup = new ProductGroup(
                "Formal Shoes",
                "men-footwear-formal-shoes",
                "men-footwear-formal-shoes-file",
                "men-footwear-formal-shoes-start-key",
                "men-footwear-formal-shoes-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Formal Shoes\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Shoe_Type_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Occasion_article_attr\",\"Heel_Height_article_attr\",\"Width_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Casual & Sports Sandals //
        productGroup = new ProductGroup(
                "Casual & Sports Sandals",
                "men-footwear-casual-and-sports-sandals",
                "men-footwear-casual-and-sports-sandals-file",
                "men-footwear-casual-and-sports-sandals-start-key",
                "men-footwear-casual-and-sports-sandals-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Sandals\\\" OR \\\"Sports Sandals\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Slippers & Flip Flops //
        productGroup = new ProductGroup(
                "Slippers & Flip Flops",
                "men-footwear-slippers-and-flip-flops",
                "men-footwear-slippers-and-flip-flops-file",
                "men-footwear-slippers-and-flip-flops-start-key",
                "men-footwear-slippers-and-flip-flops-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Flip Flops\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Flip_Flop_Type_article_attr\",\"Sole_Material_article_attr\",\"Occasion_article_attr\",\"Pattern_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);

        productHeadGroups.add(new ProductHeadGroup("Footwear", productGroups));

        ///////////////////////////// Clothing ///////////////////////
        productGroups = new ArrayList<ProductGroup>();
        // Casual Shirts //
        productGroup = new ProductGroup(
                "Casual Shirts",
                "men-clothing-casual-shirts",
                "men-clothing-casual-shirts-file",
                "men-clothing-casual-shirts-start-key",
                "men-clothing-casual-shirts-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Shirts\\\") AND global_attr_usage:(\\\"Casual\\\" OR \\\"Ethnic\\\" OR \\\"Home\\\" OR \\\"NA\\\" OR \\\"Party\\\" OR \\\"Smart Casual\\\" OR \\\"Sports\\\" OR \\\"Travel\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Fabric_article_attr\",\"Collar_article_attr\",\"Sleeve_article_attr\",\"Pattern_article_attr\",\"Fit_article_attr\",\"Occasion_article_attr\",\"Brand_Fit_Name_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // T-shirts & Collared Tees //
        productGroup = new ProductGroup(
                "T-shirts & Collared Tees",
                "men-clothing-t-shirts-and-collared-tees",
                "men-clothing-t-shirts-and-collared-tees-file",
                "men-clothing-t-shirts-and-collared-tees-start-key",
                "men-clothing-t-shirts-and-collared-tees-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Jerseys\\\" OR \\\"Polo Tshirts\\\" OR \\\"Tops\\\" OR \\\"Tshirts\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Sleeve_article_attr\",\"Neck_article_attr\",\"Pattern_article_attr\",\"Fit_article_attr\",\"Occasion_article_attr\",\"Tshirt_Type_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Formal Shirts //
        productGroup = new ProductGroup(
                "Formal Shirts",
                "men-clothing-formal-shirts",
                "men-clothing-formal-shirts-file",
                "men-clothing-formal-shirts-start-key",
                "men-clothing-formal-shirts-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Shirts\\\") AND global_attr_usage:(\\\"Formal\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Fabric_article_attr\",\"Collar_article_attr\",\"Sleeve_article_attr\",\"Pattern_article_attr\",\"Fit_article_attr\",\"Occasion_article_attr\",\"Brand_Fit_Name_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Ethnic Wear //
        productGroup = new ProductGroup(
                "Ethnic Wear",
                "men-clothing-ethnic-wear",
                "men-clothing-ethnic-wear-file",
                "men-clothing-ethnic-wear-start-key",
                "men-clothing-ethnic-wear-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Dhoti Sets\\\" OR \\\"Dhotis\\\" OR \\\"Kurta Pyjama\\\" OR \\\"Kurtas\\\" OR \\\"Kurtis\\\" OR \\\"Pyjamas\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Jeans //
        productGroup = new ProductGroup(
                "Jeans",
                "men-clothing-jeans",
                "men-clothing-jeans-file",
                "men-clothing-jeans-start-key",
                "men-clothing-jeans-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Jeans\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Fit_article_attr\",\"Waist_Rise_article_attr\",\"Denim_Treatments_article_attr\",\"Distressing_article_attr\",\"Brand_Fit_Name_article_attr\",\"Pockets_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Pants / Trousers //
        productGroup = new ProductGroup(
                "Pants & Trousers",
                "men-clothing-pants-and-trousers",
                "men-clothing-pants-and-trousers-file",
                "men-clothing-pants-and-trousers-start-key",
                "men-clothing-pants-and-trousers-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Trousers\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Fabric_article_attr\",\"Occasion_article_attr\",\"Trouser_Type_article_attr\",\"Fit_article_attr\",\"Waist_Rise_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Sweaters & Sweatshirts //
        productGroup = new ProductGroup(
                "Sweaters & Sweatshirts",
                "men-clothing-sweaters-and-sweatshirts",
                "men-clothing-sweaters-and-sweatshirts-file",
                "men-clothing-sweaters-and-sweatshirts-start-key",
                "men-clothing-sweaters-and-sweatshirts-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Sweaters\\\" OR \\\"Sweatshirts\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Jackets & Blazers //
        productGroup = new ProductGroup(
                "Jackets & Blazers",
                "men-clothing-jackets-and-blazers",
                "men-clothing-jackets-and-blazers-file",
                "men-clothing-jackets-and-blazers-start-key",
                "men-clothing-jackets-and-blazers-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Blazers\\\" OR \\\"Coats\\\" OR \\\"Jackets\\\" OR \\\"Waist Coat\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"real_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        productHeadGroups.add(new ProductHeadGroup("Clothing", productGroups));

        //////////////////////////// Accessories /////////////////////////////
        productGroups = new ArrayList<ProductGroup>();
        // Bags & Backpacks //
        productGroup = new ProductGroup(
                "Bags & Backpacks",
                "men-accessories-bags-backpacks",
                "men-accessories-bags-backpacks-file",
                "men-accessories-bags-backpacks-start-key",
                "men-accessories-bags-backpacks-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Backpacks\\\" OR \\\"Bags\\\" OR \\\"Card Holder\\\" OR \\\"Duffle Bag\\\" OR \\\"Handbags\\\" OR \\\"Laptop Bag\\\" OR \\\"Laptop Sleeve\\\" OR \\\"Luggage Bags\\\" OR \\\"Messenger Bag\\\" OR \\\"Mobile Pouch\\\" OR \\\"Netbook Bag\\\" OR \\\"Passport Holder\\\" OR \\\"Purses\\\" OR \\\"Rucksacks Bag\\\" OR \\\"Sling Bag\\\" OR \\\"String Bags\\\" OR \\\"Tablet Sleeve\\\" OR \\\"Tote Bags\\\" OR \\\"Travel Accessory\\\" OR \\\"Trolley Bag\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Belts, Ties & Cufflinks //
        productGroup = new ProductGroup(
                "Belts, Ties & Cufflinks",
                "men-accessories-belts-ties-and-cufflinks",
                "men-accessories-belts-ties-and-cufflinks-file",
                "men-accessories-belts-ties-and-cufflinks-start-key",
                "men-accessories-belts-ties-and-cufflinks-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\" Cufflinks and Pocket Square\\\" OR \\\"Belts\\\" OR \\\"Cufflinks\\\" OR \\\"Suspenders\\\" OR \\\"Tie+Cufflink+Pocket square - Combo Pack\\\" OR \\\"Ties\\\" OR \\\"Ties\\\" OR \\\"Ties and Cufflinks\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Caps & Hats //
        productGroup = new ProductGroup(
                "Caps & Hats",
                "men-accessories-caps-and-hats",
                "men-accessories-caps-and-hats-file",
                "men-accessories-caps-and-hats-start-key",
                "men-accessories-caps-and-hats-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Caps\\\" OR \\\"Hat\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Sport_Team_article_attr\",\"Occasion_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Jewellery //
        productGroup = new ProductGroup(
                "Jewellery",
                "men-accessories-jewellery",
                "men-accessories-jewellery-file",
                "men-accessories-jewellery-start-key",
                "men-accessories-jewellery-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Anklet\\\" OR \\\"Bangle\\\" OR \\\"Bracelet\\\" OR \\\"Earring & Pendant Set\\\" OR \\\"Earrings\\\" OR \\\"Jewellery\\\" OR \\\"Jewellery Set\\\" OR \\\"Key chain\\\" OR \\\"Necklace\\\" OR \\\"Pendant\\\" OR \\\"Ring\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Sunglasses //
        productGroup = new ProductGroup(
                "Sunglasses",
                "men-accessories-sunglasses",
                "men-accessories-sunglasses-file",
                "men-accessories-sunglasses-start-key",
                "men-accessories-sunglasses-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_sub_category:(\\\"Eyewear\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Wallets //
        productGroup = new ProductGroup(
                "Wallets",
                "men-accessories-wallets",
                "men-accessories-wallets-file",
                "men-accessories-wallets-start-key",
                "men-accessories-wallets-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Wallets\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Material_article_attr\",\"Wallet_Type_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Watches //
        productGroup = new ProductGroup(
                "Watches",
                "men-accessories-watches",
                "men-accessories-watches-file",
                "men-accessories-watches-start-key",
                "men-accessories-watches-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Watches\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Watch_Type_article_attr\",\"Water_Resistance_article_attr\",\"Features_article_attr\",\"Strap_Type_article_attr\",\"Occasion_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        productHeadGroups.add(new ProductHeadGroup("Accessories", productGroups));

        return productHeadGroups;
    }

    public static List<ProductHeadGroup> generateMyntraWomenProductGroups(Context context){
        List<ProductHeadGroup> productHeadGroups = new ArrayList<ProductHeadGroup>();
        List<ProductGroup> productGroups;
        ProductGroup productGroup;

        /////////////////////   Women  /////////////////////////
        ///////////////////// Footwear ////////////////////
        productGroups = new ArrayList<ProductGroup>();
        // Sandals //
        productGroup = new ProductGroup(
                "Sandals", // group label
                "women-footwear-sandals", // unique group label
                "women-footwear-sandals-file", // unique file name
                "women-footwear-sandals-start-key", // unique start key
                "women-footwear-sandals-max-products-key", // unique max products key
                "[{\"query\":\"(Shoe_Type_article_attr:(\\\"sandals\\\") AND global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Flats\\\" OR \\\"Heels\\\"))\",\"start\":", // post data head
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"  // post data tail
        );
        productGroups.add(productGroup);
        // Wedges //
        productGroup = new ProductGroup(
                "Wedges",
                "women-footwear-wedges",
                "women-footwear-wedges-file",
                "women-footwear-wedges-start-key",
                "women-footwear-wedges-max-products-key",
                "[{\"query\":\"(Heel_Type_article_attr:(\\\"wedge\\\") AND global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Heels\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Shoe_Type_article_attr\",\"Heel_Height_article_attr\",\"Heel_Type_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Toe_Shape_article_attr\",\"Ankle_Height_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // All Heels //
        productGroup = new ProductGroup(
                "All Heels",
                "women-footwear-all-heels",
                "women-footwear-all-heels-file",
                "women-footwear-all-heels-start-key",
                "women-footwear-all-heels-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Formal Shoes\\\" OR \\\"Heels\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Shoe_Type_article_attr\",\"Heel_Height_article_attr\",\"Heel_Type_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Toe_Shape_article_attr\",\"Ankle_Height_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Flats & Ballets //
        productGroup = new ProductGroup(
                "Flats & Ballets",
                "women-footwear-flats-and-ballets",
                "women-footwear-flats-and-ballets-file",
                "women-footwear-flats-and-ballets-start-key",
                "women-footwear-flats-and-ballets-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Flats\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Shoe_Type_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Toe_Shape_article_attr\",\"Ankle_Height_article_attr\",\"Sole_Material_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Slippers & Flip Flops //
        productGroup = new ProductGroup(
                "Slippers & Flip Flops",
                "women-footwear-slippers-and-flip-flops",
                "women-footwear-slippers-and-flip-flops-file",
                "women-footwear-slippers-and-flip-flops-start-key",
                "women-footwear-slippers-and-flip-flops-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Flip Flops\\\" OR \\\"Slippers\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Flip_Flop_Type_article_attr\",\"Sole_Material_article_attr\",\"Occasion_article_attr\",\"Pattern_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Casual Shoes //
        productGroup = new ProductGroup(
                "Casual Shoes",
                "women-footwear-casual-shoes",
                "women-footwear-casual-shoes-file",
                "women-footwear-casual-shoes-start-key",
                "women-footwear-casual-shoes-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Casual Shoes\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Casual_Shoe_Type_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Ankle_Height_article_attr\",\"Width_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Sports Shoes //
        productGroup = new ProductGroup(
                "Sport Shoes",
                "women-footwear-sport-shoes",
                "women-footwear-sport-shoes-file",
                "women-footwear-sport-shoes-start-key",
                "women-footwear-sport-shoes-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Sports Sandals\\\" OR \\\"Sports Shoes\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Boots //
        productGroup = new ProductGroup(
                "Boots",
                "women-footwear-boots",
                "women-footwear-boots-file",
                "women-footwear-boots-start-key",
                "women-footwear-boots-max-products-key",
                "[{\"query\":\"global_attr_gender:(\\\"women\\\") AND full_text_myntra:(\\\"boots\\\" )\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);

        productHeadGroups.add(new ProductHeadGroup("Footwear", productGroups));

        ///////////////////////////// Western Wear  ///////////////////////
        productGroups = new ArrayList<ProductGroup>();
        // Dresses //
        productGroup = new ProductGroup(
                "Dresses",
                "women-western-wear-dresses",
                "women-western-wear-dresses-file",
                "women-western-wear-dresses-start-key",
                "women-western-wear-dresses-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Dresses\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Fabric_article_attr\",\"Dress_Shape_article_attr\",\"Neck_article_attr\",\"Dress_Length_article_attr\",\"Sleeve_article_attr\",\"Knit/Woven_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Shirts //
        productGroup = new ProductGroup(
                "Shirts",
                "women-western-wear-shirts",
                "women-western-wear-shirts-file",
                "women-western-wear-shirts-start-key",
                "women-western-wear-shirts-max-products-key",
                "[{\"query\":\"(global_attr_gender:(\\\"Women\\\")) AND (global_attr_article_type_facet:(\\\"Shirts\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Fabric_article_attr\",\"Collar_article_attr\",\"Sleeve_article_attr\",\"Pattern_article_attr\",\"Fit_article_attr\",\"Occasion_article_attr\",\"Brand_Fit_Name_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Tops & Tees //
        productGroup = new ProductGroup(
                "Tops & Tees",
                "women-western-wear-tops-and-tees",
                "women-western-wear-tops-and-tees-file",
                "women-western-wear-tops-and-tees-start-key",
                "women-western-wear-tops-and-tees-max-products-key",
                "[{\"query\":\"(global_attr_article_type_facet:(\\\"Camisoles\\\" OR \\\"Lounge Top\\\" OR \\\"Tops\\\" OR \\\"Tshirts\\\") AND global_attr_gender:(\\\"Women\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Shrugs & Jackets //
        productGroup = new ProductGroup(
                "Shrugs & Jackets",
                "women-western-wear-shrugs-and-jackets",
                "women-western-wear-shrugs-and-jackets-file",
                "women-western-wear-shrugs-and-jackets-start-key",
                "women-western-wear-shrugs-and-jackets-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Blazers\\\" OR \\\"Coats\\\" OR \\\"Jackets\\\" OR \\\"Rain Jacket\\\" OR \\\"Shrug\\\" OR \\\"Waistcoat\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Sweaters & Sweatshirts //
        productGroup = new ProductGroup(
                "Sweaters & Sweatshirts",
                "women-western-wear-sweaters-and-sweatshirts",
                "women-western-wear-sweaters-and-sweatshirts-file",
                "women-western-wear-sweaters-and-sweatshirts-start-key",
                "women-western-wear-sweaters-and-sweatshirts-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Shrug\\\" OR \\\"Sweaters\\\" OR \\\"Sweatshirts\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Jeans & Leggings //
        productGroup = new ProductGroup(
                "Jeans & Leggings",
                "women-western-wear-jeans-and-leggings",
                "women-western-wear-jeans-and-leggings-file",
                "women-western-wear-jeans-and-leggings-start-key",
                "women-western-wear-jeans-and-leggings-max-products-key",
                "[{\"query\":\"(global_attr_article_type_facet:(\\\"Jeans\\\" OR \\\"Jeggings\\\") AND global_attr_gender:(\\\"Women\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Shorts & Skirts //
        productGroup = new ProductGroup(
                "Shorts & Skirts",
                "women-western-wear-shorts-and-skirts",
                "women-western-wear-shorts-and-skirts-file",
                "women-western-wear-shorts-and-skirts-start-key",
                "women-western-wear-shorts-and-skirts-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Shorts\\\" OR \\\"Skirts\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Pants / Trousers //
        productGroup = new ProductGroup(
                "Pants & Trousers",
                "women-western-wear-pants-and-trousers",
                "women-western-wear-pants-and-trousers-file",
                "women-western-wear-pants-and-trousers-start-key",
                "women-western-wear-pants-and-trousers-max-products-key",
                "[{\"query\":\"(global_attr_article_type_facet:(\\\"Trousers\\\") AND global_attr_gender:(\\\"Women\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Fabric_article_attr\",\"Occasion_article_attr\",\"Trouser_Type_article_attr\",\"Fit_article_attr\",\"Waist_Rise_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);

        productHeadGroups.add(new ProductHeadGroup("Western Wear", productGroups));

        //////////////////////////// Ethnic Wear /////////////////////////////
        productGroups = new ArrayList<ProductGroup>();
        // Sarees //
        productGroup = new ProductGroup(
                "Sarees",
                "women-ethnic-wear-sarees",
                "women-ethnic-wear-sarees-file",
                "women-ethnic-wear-sarees-start-key",
                "women-ethnic-wear-sarees-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Bridal Sari\\\" OR \\\"Fashion Sari\\\" OR \\\"Multi Saree Set\\\" OR \\\"One Minute Sari\\\" OR \\\"Partywear Sari\\\" OR \\\"Printed Sari\\\" OR \\\"Traditional Sari\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Kurtas, Kurtis & Suits //
        productGroup = new ProductGroup(
                "Kurtas, Kurtis & Suits",
                "women-ehtnic-wear-kurtas-kurtis-suits",
                "women-ehtnic-wear-kurtas-kurtis-suits-file",
                "women-ehtnic-wear-kurtas-kurtis-suits-start-key",
                "women-ehtnic-wear-kurtas-kurtis-suits-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Churidar Kurta\\\" OR \\\"Ethnic Top\\\" OR \\\"Kurtas\\\" OR \\\"Kurtis\\\" OR \\\"Lehenga Choli\\\" OR \\\"Multi Kurta Set\\\" OR \\\"Patiala Kurta Set\\\" OR \\\"SKD\\\" OR \\\"Salwar Suit\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Salwars & Churidars //
        productGroup = new ProductGroup(
                "Salwars & Churidars",
                "women-ethnic-wear-salwars-and-churidars",
                "women-ethnic-wear-salwars-and-churidars-file",
                "women-ethnic-wear-salwars-and-churidars-start-key",
                "women-ethnic-wear-salwars-and-churidars-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Churidar\\\" OR \\\"Churidar and Dupatta\\\" OR \\\"Dupatta\\\" OR \\\"Ethnic Skirt\\\" OR \\\"Harem Pants\\\" OR \\\"Jodhpuri Pants\\\" OR \\\"Moroccan Pants\\\" OR \\\"Patiala\\\" OR \\\"Patiala and Dupatta\\\" OR \\\"Pyjamas\\\" OR \\\"Salwar\\\" OR \\\"Salwar and Dupatta\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Dress Material//
        productGroup = new ProductGroup(
                "Dress Material",
                "women-ethnic-wear-dress-material",
                "women-ethnic-wear-dress-material-file",
                "women-ethnic-wear-dress-material-start-key",
                "women-ethnic-wear-dress-material-max-products-key",
                "[{\"query\":\"(global_attr_gender:(\\\"Women\\\")) AND (global_attr_article_type_facet:(\\\"Dress Material\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Fabric_article_attr\",\"Pattern_article_attr\",\"Set_Items_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);

        productHeadGroups.add(new ProductHeadGroup("Ethnic Wear", productGroups));

        //////////////////////////// Accessories /////////////////////////////
        productGroups = new ArrayList<ProductGroup>();
        // Bags & Wallets //
        productGroup = new ProductGroup(
                "Bags & Wallets",
                "women-accessories-bags-and-wallets",
                "women-accessories-bags-and-wallets-file",
                "women-accessories-bags-and-wallets-start-key",
                "women-accessories-bags-and-wallets-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Card Holder\\\" OR \\\"Clutches\\\" OR \\\"Duffle Bag\\\" OR \\\"Handbags\\\" OR \\\"Laptop Bag\\\" OR \\\"Laptop Sleeve\\\" OR \\\"Luggage Bags\\\" OR \\\"Messenger Bag\\\" OR \\\"Mobile Pouch\\\" OR \\\"Purses\\\" OR \\\"Sling Bag\\\" OR \\\"String Bags\\\" OR \\\"Tablet Sleeve\\\" OR \\\"Tote Bags\\\" OR \\\"Travel Accessory\\\" OR \\\"Trolley Bag\\\" OR \\\"Waist Pouch\\\" OR \\\"Wallets\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Sunglasses //
        productGroup = new ProductGroup(
                "Sunglasses",
                "women-accessories-sunglasses",
                "women-accessories-sunglasses-file",
                "women-accessories-sunglasses-start-key",
                "women-accessories-sunglasses-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_sub_category:(\\\"Eyewear\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Watches //
        productGroup = new ProductGroup(
                "Watches",
                "men-accessories-watches",
                "men-accessories-watches-file",
                "men-accessories-watches-start-key",
                "men-accessories-watches-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Watches\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Watch_Type_article_attr\",\"Water_Resistance_article_attr\",\"Features_article_attr\",\"Strap_Type_article_attr\",\"Occasion_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Belts //
        productGroup = new ProductGroup(
                "Belts",
                "women-accessories-belts",
                "women-accessories-belts-file",
                "women-accessories-belts-start-key",
                "women-accessories-belts-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_article_type_facet:(\\\"Belts\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Material_article_attr\",\"Reversible_article_attr\",\"Occasion_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Jewellery //
        productGroup = new ProductGroup(
                "Jewellery",
                "women-accessories-jewellery",
                "women-accessories-jewellery-file",
                "women-accessories-jewellery-start-key",
                "women-accessories-jewellery-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Unisex\\\" OR \\\"Adults-Women\\\") AND global_attr_sub_category:(\\\"Jewellery\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);

        productHeadGroups.add(new ProductHeadGroup("Accessories", productGroups));

        return productHeadGroups;
    }

    public static List<ProductHeadGroup> generateMyntraKidsProductGroups(Context context){
        List<ProductHeadGroup> productHeadGroups = new ArrayList<ProductHeadGroup>();
        List<ProductGroup> productGroups;
        ProductGroup productGroup;

        /////////////////////   Kids  /////////////////////////
        ///////////////////// Boys Apparel ////////////////////
        productGroups = new ArrayList<ProductGroup>();
        // T-shirts & Shirts //
        productGroup = new ProductGroup(
                "T-shirts & Shirts", // group label
                "kids-boys-apparel-t-shirts-and-shirts", // unique group label
                "kids-boys-apparel-t-shirts-and-shirts-file", // filename
                "kids-boys-apparel-t-shirts-and-shirts-start-key", // unique start key
                "kids-boys-apparel-t-shirts-and-shirts-max-products-key", // unique max products key
                "[{\"query\":\"(global_attr_age_group:(\\\"Kids-Boys\\\" OR \\\"Kids-Unisex\\\") AND global_attr_article_type_facet:(\\\"Shirts\\\" OR \\\"Tshirts\\\"))\",\"start\":", // post data head
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"  // post data tail
        );
        productGroups.add(productGroup);
        // Jeans, Trousers, Shorts //
        productGroup = new ProductGroup(
                "Jeans, Trousers, Shorts",
                "kids-boys-apparel-jeans-trousers-shorts",
                "kids-boys-apparel-jeans-trousers-shorts-file",
                "kids-boys-apparel-jeans-trousers-shorts-start-key",
                "kids-boys-apparel-jeans-trousers-shorts-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Kids-Boys\\\" OR \\\"Kids-Unisex\\\") AND global_attr_article_type_facet:(\\\"Jeans\\\" OR \\\"Shorts\\\" OR \\\"Trousers\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        /*
        // Sweaters, SweatShirts, Jackets //
        productGroup = new ProductGroup(
                "Sweaters, SweatShirts, Jackets",
                "kids-boys-apparel-sweaters-sweatshirts-jackets",
                "kids-boys-apparel-sweaters-sweatshirts-jackets-file",
                "kids-boys-apparel-sweaters-sweatshirts-jackets-start-key",
                "kids-boys-apparel-sweaters-sweatshirts-jackets-max-products-key",
                "",
                ""
        );
        productGroups.add(productGroup);
        */

        productHeadGroups.add(new ProductHeadGroup("Boys Apparel", productGroups));

        ///////////////////////////// Boys Footwear  ///////////////////////
        productGroups = new ArrayList<ProductGroup>();
        // Casual Shoes //
        productGroup = new ProductGroup(
                "Casual Shoes",
                "kids-boys-footwear-casual-shoes",
                "kids-boys-footwear-casual-shoes-file",
                "kids-boys-footwear-casual-shoes-start-key",
                "kids-boys-footwear-casual-shoes-max-products-key",
                "[{\"query\":\"(global_attr_gender:(\\\"Boys\\\")) AND (global_attr_article_type_facet:(\\\"Casual Shoes\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Casual_Shoe_Type_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Ankle_Height_article_attr\",\"Width_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Sport Shoes //
        productGroup = new ProductGroup(
                "Sport Shoes",
                "kids-boys-footwear-sport-shoes",
                "kids-boys-footwear-sport-shoes-file",
                "kids-boys-footwear-sport-shoes-start-key",
                "kids-boys-footwear-sport-shoes-max-products-key",
                "[{\"query\":\"(global_attr_gender:(\\\"Boys\\\")) AND (global_attr_article_type_facet:(\\\"Sports Shoes\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Sport_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Outsole_article_attr\",\"Cleats_article_attr\",\"Width_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        /*
        // Sandals & Clogs //
        productGroup = new ProductGroup(
                "Sandals & Clogs",
                "kids-boys-footwear-sandals-and-clogs",
                "kids-boys-footwear-sandals-and-clogs-file",
                "kids-boys-footwear-sandals-and-clogs-start-key",
                "kids-boys-footwear-sandals-and-clogs-max-products-key",
                "",
                ""
        );
        productGroups.add(productGroup);
        */

        productHeadGroups.add(new ProductHeadGroup("Boys Footwear", productGroups));

        //////////////////////////// Girls Apparel /////////////////////////////
        productGroups = new ArrayList<ProductGroup>();
        // Frocks //
        productGroup = new ProductGroup(
                "Frocks",
                "kids-girls-apparel-frocks",
                "kids-girls-apparel-frocks-file",
                "kids-girls-apparel-frocks-start-key",
                "kids-girls-apparel-frocks-max-products-key",
                "[{\"query\":\"(global_attr_gender:(\\\"Girls\\\")) AND (global_attr_article_type_facet:(\\\"Dresses\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Fabric_article_attr\",\"Dress_Shape_article_attr\",\"Neck_article_attr\",\"Dress_Length_article_attr\",\"Sleeve_article_attr\",\"Knit/Woven_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Tops & T-shirts //
        productGroup = new ProductGroup(
                "Tops & T-shirts",
                "kids-girls-apparel-tops-and-t-shirts",
                "kids-girls-apparel-tops-and-t-shirts-file",
                "kids-girls-apparel-tops-and-t-shirts-start-key",
                "kids-girls-apparel-tops-and-t-shirts-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Kids-Girls\\\" OR \\\"Kids-Unisex\\\") AND global_attr_article_type_facet:(\\\"Shirts\\\" OR \\\"Tops\\\" OR \\\"Tshirts\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        // Bottomwear //
        productGroup = new ProductGroup(
                "Bottomwear",
                "kids-girls-apparel-bottomwear",
                "kids-girls-apparel-bottomwear-file",
                "kids-girls-apparel-bottomwear-start-key",
                "kids-girls-apparel-bottomwear-max-products-key",
                "[{\"query\":\"(global_attr_gender:(\\\"Girls\\\")) AND (global_attr_sub_category_facet:(\\\"Bottomwear\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        /*
        // Sweaters, Sweatshirts, Jackets //
        productGroup = new ProductGroup(
                "Sweaters, Sweatshirts, Jackets",
                "kids-girls-apparel-sweaters-sweatshirts-jackets",
                "kids-girls-apparel-sweaters-sweatshirts-jackets-file",
                "kids-girls-apparel-sweaters-sweatshirts-jackets-start-key",
                "kids-girls-apparel-sweaters-sweatshirts-jackets-max-products-key",
                "",
                ""
        );
        productGroups.add(productGroup);
        */

        productHeadGroups.add(new ProductHeadGroup("Girls Apparel", productGroups));

        //////////////////////////// Girls Footwear /////////////////////////////
        productGroups = new ArrayList<ProductGroup>();
        /*
        // Ballets //
        productGroup = new ProductGroup(
                "Ballets",
                "kids-girls-footwear-ballets",
                "kids-girls-footwear-ballets-file",
                "kids-girls-footwear-ballets-start-key",
                "kids-girls-footwear-ballets-max-products-key",
                "",
                ""
        );
        productGroups.add(productGroup);
        */

        // Shoes //
        productGroup = new ProductGroup(
                "Shoes",
                "kids-girls-footwear-shoes",
                "kids-girls-footwear-shoes-file",
                "kids-girls-footwear-shoes-start-key",
                "kids-girls-footwear-shoes-max-products-key",
                "[{\"query\":\"(global_attr_gender:(\\\"Girls\\\")) AND (global_attr_sub_category_facet:(\\\"Shoes\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_female_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        /*
        // Sandals & Clogs //
        productGroup = new ProductGroup(
                "Sandals & Clogs",
                "kids-girls-footwear-sandals-and-clogs",
                "kids-girls-footwear-sandals-and-clogs-file",
                "kids-girls-footwear-sandals-and-clogs-start-key",
                "kids-girls-footwear-sandals-and-clogs-max-products-key",
                "",
                ""
        );
        productGroups.add(productGroup);
        */
        productHeadGroups.add(new ProductHeadGroup("Girls Footwear", productGroups));

        //////////////////////////// Accessories /////////////////////////////
        productGroups = new ArrayList<ProductGroup>();
        /*
        // Bags & Backpacks //
        productGroup = new ProductGroup(
                "Bags & Backpacks",
                "kids-accessories-bags-and-backpacks",
                "kids-accessories-bags-and-backpacks-file",
                "kids-accessories-bags-and-backpacks-start-key",
                "kids-accessories-bags-and-backpacks-max-products-key",
                "",
                ""
        );
        productGroups.add(productGroup);
        */

        // Watches //
        productGroup = new ProductGroup(
                "Watches",
                "kids-accessories-watches",
                "kids-accessories-watches-file",
                "kids-accessories-watches-start-key",
                "kids-accessories-watches-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Kids-Boys\\\" OR \\\"Kids-Girls\\\" OR \\\"Kids-Unisex\\\") AND global_attr_article_type_facet:(\\\"Watches\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Watch_Type_article_attr\",\"Water_Resistance_article_attr\",\"Features_article_attr\",\"Strap_Type_article_attr\",\"Occasion_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);
        /*
        // Jewellery //
        productGroup = new ProductGroup(
                "Jewellery",
                "kids-accessories-jewellery",
                "kids-accessories-jewellery-file",
                "kids-accessories-jewellery-start-key",
                "kids-accessories-jewellery-max-products-key",
                "",
                ""
        );
        productGroups.add(productGroup);
        */
        // Sunglasses //
        productGroup = new ProductGroup(
                "Sunglasses",
                "kids-accessories-sunglasses",
                "kids-accessories-sunglasses-file",
                "kids-accessories-sunglasses-start-key",
                "kids-accessories-sunglasses-max-products-key",
                "[{\"query\":\"(global_attr_age_group:(\\\"Kids-Boys\\\" OR \\\"Kids-Girls\\\" OR \\\"Kids-Unisex\\\") AND global_attr_article_type_facet:(\\\"Sunglasses\\\"))\",\"start\":",
                ",\"rows\":96,\"facetField\":[\"Sunglasses_Type_article_attr\",\"Lens_Colour_article_attr\",\"Feature_article_attr\",\"Frame_Material_article_attr\",\"Extras_Included_article_attr\",\"Warranty_article_attr\",\"Occasion_article_attr\",\"Frame_Style_article_attr\"],\"fq\":[\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store21_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true,\"facet\":true}]"
        );
        productGroups.add(productGroup);

        productHeadGroups.add(new ProductHeadGroup("Accessories", productGroups));

        return productHeadGroups;
    }
}
