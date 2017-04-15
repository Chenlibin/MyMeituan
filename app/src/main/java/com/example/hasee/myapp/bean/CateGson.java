package com.example.hasee.myapp.bean;

import java.util.List;

/**
 * Created by hasee on 2017/4/7.
 */
public class CateGson {



    public GsonData data;

    public GsonData getData() {
        return data;
    }

    public void setData(GsonData data) {
        this.data = data;
    }

    public static class GsonData{
        public PoiList poiList;

        public PoiList getPoiList() {
            return poiList;
        }

        public void setPoiList(PoiList poiList) {
            this.poiList = poiList;
        }

        public static class PoiList{
            public List<PoiInfos> poiInfos;

            public List<PoiInfos> getPoiInfos() {
                return poiInfos;
            }

            public void setPoiInfos(List<PoiInfos> poiInfos) {
                this.poiInfos = poiInfos;
            }

            public static class PoiInfos{

                public String avgPrice;
                public String avgScore;
                public String cateName;
                public String frontImg;
                public String name;
                public List<ExtraServiceTags> extraServiceTags;
                public PreferentialInfo preferentialInfo;


                public String getAvgPrice() {
                    return avgPrice;
                }

                public void setAvgPrice(String avgPrice) {
                    this.avgPrice = avgPrice;
                }

                public String getAvgScore() {
                    return avgScore;
                }

                public void setAvgScore(String avgScore) {
                    this.avgScore = avgScore;
                }

                public String getCateName() {
                    return cateName;
                }

                public void setCateName(String cateName) {
                    this.cateName = cateName;
                }

                public String getFrontImg() {
                    return frontImg;
                }

                public void setFrontImg(String frontImg) {
                    this.frontImg = frontImg;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public PreferentialInfo getPreferentialInfo() {
                    return preferentialInfo;
                }

                public void setPreferentialInfo(PreferentialInfo preferentialInfo) {
                    this.preferentialInfo = preferentialInfo;
                }

                public List<ExtraServiceTags> getExtraServiceTags() {
                    return extraServiceTags;
                }

                public void setExtraServiceTags(List<ExtraServiceTags> extraServiceTags) {
                    this.extraServiceTags = extraServiceTags;
                }

                public static class ExtraServiceTags{
                    public Text text;

                    public Text getText() {
                        return text;
                    }

                    public void setText(Text text) {
                        this.text = text;
                    }

                    public static class Text{
                        public String content;

                        public String getContent() {
                            return content;
                        }

                        public void setContent(String content) {
                            this.content = content;
                        }
                    }


                }

                public static class PreferentialInfo{
                    public Maidan maidan;

                    public Maidan getMaidan() {
                        return maidan;
                    }

                    public void setMaidan(Maidan maidan) {
                        this.maidan = maidan;
                    }

                    public static class Maidan{
                        public List<Entries> entries;

                        public static class Entries{
                            public String content;
                            public String icon;

                            public String getContent() {
                                return content;
                            }

                            public void setContent(String content) {
                                this.content = content;
                            }

                            public String getIcon() {
                                return icon;
                            }

                            public void setIcon(String icon) {
                                this.icon = icon;
                            }
                        }

                    }

                }

            }

        }

    }

}
