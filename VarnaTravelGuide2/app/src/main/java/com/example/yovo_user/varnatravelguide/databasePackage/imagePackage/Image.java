package com.example.yovo_user.varnatravelguide.databasePackage.imagePackage;

public class Image {

    private int id;
    private int placeId;
    private String imageURL;
    private int isMainImage;

    public Image(int id, int placeId, String imageURL) {
        this.id = id;
        this.placeId = placeId;
        this.imageURL = imageURL;
    }

    public Image(int placeId, String imageURL,int isMainImage) {
        this.placeId = placeId;
        this.imageURL = imageURL;
        this.isMainImage = isMainImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getIsMainImage() {
        return isMainImage;
    }

    public void setIsMainImage(int isMainImage) {
        this.isMainImage = isMainImage;
    }

    public static Image[] populateImages() {
        return new Image[]{

                ////////// RESTAURANTS //////////

                //Blacksheep Brewhouse
                new Image(1,
                        "18GgFMz9RbIq86kY8JGPimhiNyJrAJy8j",1),
                new Image(1,
                        "1LG8cLZP8pk89qubGaT1h6Bme5wc4zFt3",0),
                new Image(1,
                        "1wBBfnfqPFleDqkpdgLgQXijIKPN2cY5F",0),
                new Image(1,
                        "1fy638D7Rlv3huJR5uALXbbUAQWEJKXTy",0),
                new Image(1,
                        "1mbtSiPjiQi0Mc3Hr2zvBYWgQoeigEtCZ",0),


                //La Mer Restaurant&Bar
                new Image(2,
                        "1TtVjaoNDu2Sm6qJV6lipGWoZWWKzWO4t",1),
                new Image(2,
                        "1RS8bXD6HpO_MKLM5ig279unp1LetJCt8",0),
                new Image(2,
                        "18kmpZ6vt6ERHTI3oPdwLF7GkV-z_F0gz",0),
                new Image(2,
                        "12jUq9fR36h5bzeMznf5Y2BhsLZBLpnx1",0),
                new Image(2,
                        "1IHRo3kPNacHNBIEn8p-PZki0owdtIy-S",0),
                new Image(2,
                        "1cPuhcRHmKSKyCRj_6NcbKf4BCcqMpmC7",0),

                //Musala restaurant
                new Image(3,
                        "11G0hD8kxzlmJd7N-aSbagsPFsrcZhB1G",1),
                new Image(3,
                        "10K0Gs4CeNVkzAi-Dx5P8a3XEU0xiKeag",0),
                new Image(3,
                        "1fEwhQ583R8g4Vw7z1KIGWc__kvS-x4xL",0),
                new Image(3,
                        "1o6uq8z7uvyzaboEBJGhJESDxDWfnKYRb",0),
                new Image(3,
                        "1-t6et-y5fRD7Y-Nfy99qErPbpBmKgSSb",0),

                //Pizza place - Pizzarela
                new Image(4,
                        "1PwF0krGHER58EYVw-9tgnbvTuXYYjzeq",1),
                new Image(4,
                        "1PSvSbLWCHmnnh-sX7BhcYuplbW658KgM",0),
                new Image(4,
                        "1W46Hx2rMtmpSDXWuTztC4lxrB0lRgXRj",0),
                new Image(4,
                        "1E2QlJAzqJQDxwn5MYQFNtiYxHEKA9J8C",0),
                new Image(4,
                        "1TfXpRh5yWaUkwdyI5NX0ZKirrNbwFiV4",0),

                //Vulsheben Izovor - tavern

                new Image(5,
                        "1HT5qzIhQw_tzIPIlR7in81O4VdNBBcKE",1),
                new Image(5,
                        "1J1jWV5rh595nQI-lAWw4ZCQo3yBgbvJu",0),
                new Image(5,
                        "1bwI4bW4vn750wwHvrznstkePU3SOe1b6",0),
                new Image(5,
                        "1uXchsth18CW3nccNJbvi6bLEHv2EhiXt",0),
                new Image(5,
                        "1b9Iq0ENLdaU3RqYUBty1Ybje4_uW577i",0),

                ////////// SHOPPING PLACES //////////

                //grand mall varna
                new Image(6,
                        "11CGJUWilR1WTmBsLrtImORN1x437Ic9H",0),
                new Image(6,
                        "1m_SYEm20gorvnnYNyp3dF2ZQO0Uu8TiB",1),
                new Image(6,
                        "1JtBk_X3A5DBP62qayZhFiQHgcg_FOu2C",0),
                new Image(6,
                        "1s-YKuhCc8RhMYQup_pCScs8-cjnChc14",0),
                new Image(6,
                        "1g4RfcBo8xUC-5KiKtWsR3Ewn1OURzCZb",0),


                //LOLLIPOP
                new Image(7,
                        "10iwm17IFlEVVqCJT76mN7kIapEB0Q-_K",1),
                new Image(7,
                        "1BP5bq8cY8wmVajFbGwsKABLLWuw1nM9o",0),
                new Image(7,
                        "1pSw_zoeNZcSZXln2hlwhdKjBza8FKB4n",0),
                new Image(7,
                        "1Iyngoz_nDNaEzaAtFSpMrjFPUVUeHcoi",0),
                new Image(7,
                        "1xnLPKOf-bLD8r7sEqdz9Ug9TapmLbkTZ",0),


                //Picadilly Park
                new Image(8,
                        "1C4llwGNVD2seTyVkL8zWgSFUKEb8OuSg",1),
                new Image(8,
                        "1aPoPNKIuQ5pZo3eYJ1lpQMpHSdmvLKoR",0),
                new Image(8,
                        "1vTPS9WMpAD2u4lUOvzpI7-it1v_5vt4c",0),
                new Image(8,
                        "19AO2DR7ZijOaCisH2uX5Mepu56hF8_Oz",0),
                new Image(8,
                        "11Djj1YE9O3kd5NrMo3CGbBYYyZNJi65C",0),

                //Retail Park Varna
                new Image(9,
                        "17elrSmrHHGnX588ncgWzNnlv3havIXky",1),
                new Image(9,
                        "1-j2Mx9qIzQB4iBOwKW_huj6LhCY5SR6x",0),
                new Image(9,
                        "1MWMWvCX8mfY-XPOsQ6xhoWXzf_uOu89_",0),
                new Image(9,
                        "1kTeQsv3d20raw_r3CoNYAlvjBHGUbej4",0),
                new Image(9,
                        "16OIF9a6zBK-PjdJtkmOoDNvcarjehB_y",0),

                //Tina Art Gallery
                new Image(10,
                        "1sFe59kkWhm4jUOKSY6bJKyA1X6h4oyXt",0),
                new Image(10,
                        "1_K4deZAi2gI2o5JrJAWyiLUglZv8C3ib",0),
                new Image(10,
                        "16LLfuvEj6O_nTsfMoq3xq1YNDtqP1Mwn",0),
                new Image(10,
                        "1N86E6g8SCg1_5mq8fmZ-CRZZGmEaCCJ9",0),
                new Image(10,
                        "1C0kIceiJXvVArQTR8oMqJJC344Z3VsGa",1),


                ////////// HOTELS //////////

                //Акрополис
                new Image(11,
                        "1dhQc8Pi7f12b0H49qJuDoB32sYfqTFfH",1),
                new Image(11,
                        "1HN5CoUFeOE_ztGo6ewxTeozq0R9Tq2z6",0),
                new Image(11,
                        "1Ox9LhM_EJIis1GdDF9BEc79CwsszsMTM",0),
                new Image(11,
                        "1DO-lm6_flmc7ZSq8oayHfmYL7SBh2sXY",0),
                new Image(11,
                        "1LWyZaGsgFg7BZ_LIOJKpZJsXGfW4iETJ",0),


                //Вила Жана -къща за гости
                new Image(12,
                        "119QK-n8eRVUlD_0e9GOL3qRjtDdcOiRS",1),
                new Image(12,
                        "10h-OnTd9beeftvUw4umaIz77Z_gp99Ob",0),
                new Image(12,
                        "1llNapNuZL8gYyuPOp19VIKGuBsv-RMym",0),
                new Image(12,
                        "1d9rdFzi_9Q45DlVxb42PcnKxKTpK6Cjc",0),
                new Image(12,
                        "1AQS0wsH6N1ZxtkLoOX2yLcy2_OsSy5wU",0),
                new Image(12,
                        "1WmKsCae90E4s0oTD0vt6HblCGMqOtosn",0),
                new Image(12,
                        "1_Yzf8ZFTHRcARRgJ76s6_SI4Kju0lSAu",0),
                new Image(12,
                        "10AWn1TcJ2L4WMsdK0n-UXH1Wm-3nEFR8",0),


                //Дукеса_3
                new Image(13,
                        "1N-0eRGya40g9358TOjkVBdW0-kYGSAdE",0),
                new Image(13,
                        "1BQUeiKwQ2tcZd6tOOHZ0gMzSjpr5WzqF",1),
                new Image(13,
                        "15JymGfCXrYfoGf-sto_IJ7CkGn2DDOUp",0),
                new Image(13,
                        "11r9u5SSQjZyJLlbKoscP8YlMMyLooLHg",0),
                new Image(13,
                        "1zrXhqzH2eyQMch9cWh0sTUJYMMzZYP83",0),

                //Рослин Димят Варна_5
                new Image(14,
                        "1btCtGR_52ymgajJb0jm3QBfJlf2JjeB9",1),
                new Image(14,
                        "1uxTRssCqYCH7j9f-I9LoOWhcDxJJ6lnh",0),
                new Image(14,
                        "10ezwuq0BEaPOyHL9XxoaaSisYgJT3VZc",0),
                new Image(14,
                        "1JwzFfQmvnqc1qaRARc72andzIyzA_O6j",0),
                new Image(14,
                        "1Y5ez0vS9oRKvmFl5NxlGWse5i8HhzxiK",0),
                new Image(14,
                        "1S2Q8aC7wb1f3TenW-9EGpJSCx7XgXntB",0),


                //Сплендид_4
                new Image(15,
                        "1mC2ARn_uw0hK__t2GpIaJnIiJd-z4Jzy",1),
                new Image(15,
                "15bupuhTRwzbZB_4hMJB8xl7XoTC3MGlj",0),
                new Image(15,
                        "15PKreK7CvHnPWTLe8dMqWN5JUb1aTrSA",0),
                new Image(15,
                        "1l7fSNT68YoRRgeX5kTX7V_SfTV2G0bET",0),
                new Image(15,
                        "1eTedJWvQCS07soa02uWq7_j6nW66f2xC",0),

                new Image(15,
                        "1nz9fXJLRL12462yGrGON7ULlogYVmExS",0),
                new Image(15,
                        "1IlPpvtK6Wz612LFLRc-N6CkQMM1o1kTt",0),

                ////////// LANDMARKS //////////

                //Pobiti Kamani
                new Image(16,
                        "1pKyUAG8NRzjzColUaDVRK5ysl2uzcKp6",0),
                new Image(16,
                        "1e9Aze7q35ZpmYSVOKGswm69x0JfQLVX7",1),
                new Image(16,
                        "1nUTflEmD6hT2jWiNQm_syDwEMPheFtxY",0),
                new Image(16,
                        "19LDA7mjOipJ1Ra_P63GDPCaoVf9XDc3t",0),


                //Patleina monastery
                new Image(17,
                        "1ePkLar7WLbevMWHXGVaCualw3Zq3F9la",1),
                new Image(17,
                        "1QhsWQIy19cjfL5Zog33GhvsnIvU-tPW0",0),
                new Image(17,
                        "1QIJiXg6MA2WkCyNfvGMOamWuplVJ4-g8",0),
                new Image(17,
                        "1OIS2KlPzrYp6pOFW93zb8WR8R_kxj2ME",0),
                new Image(17,
                        "1_NaNjBbQPqIT6JWMAhGeXIUz1EnOwskI",0),



                //Palace of Omurtag
                new Image(18,
                        "1Y1yhTy4ZkHtZG8VeRBp4FAskAhc641Hr",1),
                new Image(18,
                        "1HGhushqMp3dkKku7wZXtRHYOCGL_ZqEp",0),
                new Image(18,
                        "11yhdVqAIvVzVYxPMKOYxF9Ll87lyL8MR",0),
                new Image(18,
                        "16UHeQpN03qhWMLEosmkGHU00kmZwWE-m",0),
                new Image(18,
                        "1oN-7OwJWuybD-gXgBwkHkhhhUhXtA3Sl",0),
                new Image(18,
                        "1QMGfdwDibgIcd03FSh7GxGS6LfpKUzGW",0),
                new Image(18,
                        "1QU-OvV4fqR8PgPl_0RmGOk12wzPxHMS8",0),




                //Madara Rider
                new Image(19,
                        "1M9-GFAKp8BmZCeukA6BzPWxMDrYFboXB",0),
                new Image(19,
                        "1A71wNup3M84pKfpFdkzPAZ8hu_8ACCyg",0),
                new Image(19,
                        "1UNHOouu0r9k2xShP-Wdj2k9k7Z_oaPRr",1),
                new Image(19,
                        "1stkLQEiGmerud2Oi_QfpXHpNBsQPqBbn",0),
                new Image(19,
                        "1wI7XDNubEc9BezvB39yLgRUxPaZdMjdK",0),
                new Image(19,
                        "1yolt28gi3UaV_AzqXc25lOYvmxorvnw5",0),
                new Image(19,
                        "1D6q6u7bMbibs5aXdj_Mj3kj-nxRWRMAo",0),



                //Regional History Museum of Varna
                new Image(20,
                        "1Y_hJJ8U00gcqfzIYs_Fcj0FPqJFtojU8",1),
                new Image(20,
                        "1eQAV18q-9g4yG8m2lgYwLOzFYjGfefrY",0),
                new Image(20,
                        "1SWLJU_s8mxD9It8quYBFRU7rMqAg-EMw",0),
                new Image(20,
                        "1KUOjDIfUw3WCzvSpYQe2Xj082LRODQ37",0),
                new Image(20,
                        "1PniiFpdgIQRvsGirJDSqUf2VXODvQgKQ",0),
        };
    }
}
