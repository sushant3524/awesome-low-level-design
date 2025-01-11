

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Date: 9/9/13
 * Time: 9:03 PM
 *
 * @author Abhishek Sanoujam
 */
public enum SupportedLanguage {

    Abkhazian("Abkhazian", "æbˈkɑːz", "ab", "custom_icu"),
    Afar("Afar", "Qafár", "aa", "custom_icu"),
    Afrikaans("Afrikaans", "Afrikaans", "af", "custom_icu"),
    Akan("Akan", "Akan", "ak", "custom_icu"),
    Albanian("Albanian", "Shqip", "sq", "custom_icu"),
    Amharic("Amharic", "አማርኛ", "am", "custom_icu"),
    Arabic("Arabic", "العربية", "ar", "custom_icu", 4),
    Armenian("Armenian", "Hayeren", "hy", "custom_icu"),
    Assamese("Assamese", "অসমীয়া", "as", "custom_icu"),
    Aymara("Aymara", "Aymar aru", "ay", "custom_icu"),
    Azerbaijani("Azerbaijani", "Azərbaycan Dili", "az", "custom_icu"),
    Bambara("Bambara", "Bámánánkán", "bm" , "custom_icu"),
    Bashkir("Bashkir", "Башҡортса", "ba", "custom_icu"),
    Basque("Basque", "Euskara", "eu", "custom_icu"),
    Belarusian("Belarusian", "беларуская", "be", "custom_icu"),
    Bengali("Bengali", "Bangla", "bn", "custom_icu"),
    Bihari("Bihari", "भोजपुरी", "bh", "custom_icu"),
    Bislama("Bislama", "biʃlamaʁ", "bi", "custom_icu"),
    Bosnian("Bosnian", "Bosnian", "bs", "custom_icu"),
    Breton("Breton", "bʁeˈzɔ̃ːnɛk", "br", "custom_icu"),
    Bulgarian("Bulgarian", "Български", "bg", "custom_icu"),
    Catalan("Catalan", "Català", "ca", "custom_icu"),
    Cebuano("Cebuano", "Bisaya", "ceb", "custom_icu"),
    Cherokee("Cherokee", "Tsalagi", "chr", null),
    Chinese("Chinese", "中文", "zh", "smartcn", 2),
    Chinese_Cantonese("Chinese Cantonese", "中文粵語", "zh-hk", SupportedLanguage.Chinese, "smartcn", false),
    Chinese_Traditional("Chinese Traditional", "中國傳統", "zh-tw", SupportedLanguage.Chinese, "smartcn", "zh-TW",false),
    Corsican("Corsican", "Corsu", "co", "custom_icu"),
    Croatian("Croatian", "Hrvatski", "hr", "custom_icu"),
    Czech("Czech", "Čeština", "cs", "custom_icu"),
    Danish("Danish", "Dansk", "da", "custom_icu"),
    Dogri("Dogri", "डोगरी", "doi","custom_icu"),
    Dutch("Dutch", "Nederlands", "nl", "custom_icu"),
    Dzongkha("Dzongkha", "རྫོང་ཁ་", "dz", "custom_icu"),
    English("English", "English", "en", "en", 1),
    Esperanto("Esperanto", "Esperanto", "eo", "custom_icu"),
    Estonian("Estonian", "Eesti", "et", "custom_icu"),
    Ewe("Ewe", "Èʋegbe", "ee", "custom_icu"),
    Faroese("Faroese", "føroyskt mál", "fo", "custom_icu"),
    Fijian("Fijian", "Na vosa vaka-Viti", "fj", "custom_icu"),
    Filipino("Pilipino", "Wikang Filipino", "fil", "custom_icu"),
    Finnish("Finnish", "Suomi", "fi", "custom_icu"),
    French("French", "Français", "fr", "fr"),
    Frisian("Frisian", "Frysk", "fy", "custom_icu"),
    Galician("Galician", "Galego", "gl", "custom_icu"),
    Ganda("Ganda", "Luganda", "lg", null),
    Georgian("Georgian", "ქართული", "ka", "custom_icu"),
    German("German", "Deutsch", "de", "custom_icu"),
    Greek("Greek", "Ελληνικά", "el", "custom_icu"),
    Guaraní("Guaraní", "Guaraní", "gn", "custom_icu"),
    Gujarati("Gujarati", "ગુજરાતી", "gu", "custom_icu"),
    Haitian_Creole("Haitian Creole", "Kreyòl ayisyen", "ht", "custom_icu"),
    Hausa("Hausa", "Hausa", "ha", "custom_icu"),
    Hawaiian("Hawaiian", "ʻŌlelo Hawaiʻi", "haw", "custom_icu"),
    Hebrew("Hebrew", "עברית", "he", "custom_icu"),
    Hebrew_1("Hebrew", "עברית", "iw", "custom_icu", true),
    Hindi("Hindi", "हिंदी", "hi", "custom_icu"),
    Hmong("Hmong", "Mong", "hmn", null),
    Hungarian("Hungarian", "Magyar", "hu", "custom_icu"),
    Icelandic("Icelandic", "Íslenska", "is", "custom_icu"),
    Igbo("Igbo", "Igbo", "ig", "custom_icu"),
    Ilocano("Ilocano", "Ilokano", "ilo" , "custom_icu"),
    Indonesian("Indonesian", "Indonesian", "id", "custom_icu", 5),
    Indonesian_1("Indonesian", "Indonesian", "in", null, true),
    Interlingua("Interlingua", "Interlingua", "ia", "custom_icu"),
    Interlingue("Interlingue", "Interlingue", "ie", "custom_icu"),
    Inuktitut("Inuktitut", "ᐃᓄᒃᑎᑐᑦ", "iu", null),
    Irish("Irish", "Gaeilge", "ga", "ga"),
    Italian("Italian", "Italiano", "it", "custom_icu"),
    Japanese("Japanese", "日本語", "ja", "ja"),
    Javanese("Javanese", "Jawa", "jv", "custom_icu"),
    Javanese_1("Javanese", "Jawa", "jw", "custom_icu", true),
    Kannada("Kannada", "ಕನ್ನಡ", "kn", "custom_icu"),
    Kashmiri("Kashmiri", "कॉशुर", "ks", "custom_icu"),
    Kazakh("Kazakh", "қазақ", "kk", "custom_icu"),
    Khasi("Khasi", "ক ক্ত্যেন খসি", "kha", "custom_icu"),
    Khmer("Khmer", "Cambodian", "km", null),
    Kinyarwanda("Kinyarwanda", "Ikinyarwanda", "rw", null),
    Konkani("Konkani", "कोंकणी", "gom", "custom_icu"),
    Korean("Korean", "한국어", "ko", "ko"),
    Kurdish("Kurdish", "Kurdî", "ku", "custom_icu"),
    Kyrgyz("Kyrgyz", "Кыргызча", "ky", "custom_icu"),
    Lao("Lao", "ພາສາລາວ", "lo", null),
    Latin("Latin", "Latinus", "la", "custom_icu"),
    Latvian("Latvian", "Latviešu", "lv", "custom_icu"),
    Limbu("Limbu", "Yakthung Pān", "lif", null),
    Lingala("Lingala", "Ngala", "ln", "custom_icu"),
    Lithuanian("Lithuanian", "Lietuvių", "lt", "custom_icu"),
    Luxembourgish("Luxembourgish", "lëtzebuergesch", "lb", "custom_icu"),
    Macedonian("Macedonian", "македонски", "mk", "custom_icu"),
    Maithili("Maithili", "मैथिली", "mai", "custom_icu"),
    Malagasy("Malagasy", "Malagasy", "mg", "custom_icu"),
    Malay("Malay", "Melayu", "ms", "custom_icu"),
    Malayalam("Malayalam", "മലയാളം", "ml", "custom_icu"),
    Maldivian("Maldivian", "Divehi", "dv", null),
    Maltese("Maltese", "Malti", "mt", "custom_icu"),
    Manx("Manx", "Manx", "gv", "custom_icu"),
    Maori("Maori", "Māori", "mi", "custom_icu"),
    Marathi("Marathi", "Marāṭhī", "mr", "custom_icu"),
    Mongolian("Mongolian", "Монгол", "mn", "custom_icu"),
    Mizo("Mizo", "Mizo ṭawng", "lus", "custom_icu"),
    Myanmar("Myanmar", "Burmese", "my", null),
    Nauru("Nauru", "Nauru", "na", "custom_icu"),
    Nepali("Nepali", "नेपाली", "ne", "custom_icu"),
    Northern_Sotho("Northern Sotho", "Sesotho sa Leboa", "nso", "custom_icu"),
    Norwegian("Norwegian", "Norsk", "no", "custom_icu"),
    Norwegian_1("Norwegian", "Norsk", "nb", null, true),
    Nyanja("Nyanja", "Nyanja", "ny", "custom_icu"),
    Occitan("Occitan", "leŋɡɔ ˈðɔ(k)", "oc", "custom_icu"),
    Oriya("Oriya", "ଓଡ଼ିଆ", "or", "custom_icu"),
    Oromo("Oromo", "Afaan Oromoo", "om", "custom_icu"),
    Panjabi("Punjabi", "ਪੰਜਾਬੀ", "pa", "custom_icu"),
    Papiamento("Papiamento", "Papiamentu", "pap", "custom_icu"),
    Pashto("Pashto", "Pashto", "ps", "custom_icu"),
    Persian("Persian", "Farsi", "fa", "custom_icu"),
    Polish("Polish", "Polski", "pl", "custom_icu"),
    Portuguese("Portuguese(BR)", "Português", "pt", "custom_icu"),
    Portuguese_pt("Portuguese(PT)", "Português", "pt_pt", "custom_icu"),
    Quechua("Quechua", "Kechua", "qu", "custom_icu"),
    Rajasthani("Rajasthani", "राजस्थानी", "raj", "custom_icu"),
    Romanian("Romanian", "Română", "ro", "custom_icu"),
    Rundi("Rundi", "Rundi", "rn", "custom_icu"),
    Russian("Russian", "Русский", "ru", "custom_icu"),
    Samoan("Samoan", "Samoa", "sm", "custom_icu"),
    Sango("Sango", "yângâ tî sängö", "sg", "custom_icu"),
    Sanskrit("Sanskrit", "संस्कृतम्", "sa", "custom_icu"),
    Scots_Gaelic("Scottish Gaelic", "Gàidhlig", "gd", null),
    Serbian("Serbian", "српски", "sr", "custom_icu"),
    Sesotho("Sesotho", "Sesotho", "st", "custom_icu"),
    Shona("Shona", "Shona", "sn", "custom_icu"),
    Sindhi("Sindhi", "Sindhi", "sd", "custom_icu"),
    Sinhala("Sinhalese", "Sinhala", "si", "custom_icu"),
    Slovak("Slovak", "Slovenský", "sk", "custom_icu"),
    Slovene("Slovene", "Slovenski", "sl", "custom_icu"),
    Somali("Somali", "Soomaaliga", "so", "custom_icu"),
    Sorani_Kurdish("Sorani Kurdish", "Sorani Kurdish", "ckb", null),
    Spanish("Spanish", "Español", "es", "custom_icu", 3),
    Sundanese("Sundanese", "basa Sunda", "su", "custom_icu"),
    Swahili("Swahili", "Kiswahili", "sw", "custom_icu"),
    Swedish("Swedish", "Svenska", "sv", "custom_icu"),
    Syriac("Syriac", "ܠܫܢܐ ܣܘܪܝܝܐ", "syr", "custom_icu"),
    Tagalog("Filipino", "Tagalog", "tl", "custom_icu"),
    Tajik("Tajik", "Тоҷикӣ", "tg", "custom_icu"),
    Tamil("Tamil", "தமிழ்", "ta", "custom_icu"),
    Tatar("Tatar", "татар теле", "tt", "custom_icu"),
    Telugu("Telugu", "తెలుగు", "te", "custom_icu"),
    Thai("Thai", "ไทย", "th", "th"),
    Tibetan("Tibetan", "བོད་སྐད", "bo", null),
    Tigirinya("Tigirinya", "ትግርኛ", "ti", "custom_icu"),
    Tongan("Tongan", "Tongan", "to", "custom_icu"),
    Tsonga("Tsonga", "Xitsonga", "ts", "custom_icu"),
    Tswana("Tswana", "Setswana", "tn", "custom_icu"),
    Tumbuka("Tumbuka", "Tumbuka", "tum", "custom_icu"),
    Turkish("Turkish", "Türkçe", "tr", "custom_icu"),
    Turkmen("Turkmen", "türkmençe", "tk", "custom_icu"),
    Ukrainian("Ukrainian", "українська", "uk", "custom_icu"),
    Urdu("Urdu", "ﺍﺭﺩﻭ", "ur", "custom_icu"),
    Uyghur("Uyghur", "ئۇيغۇر", "ug", null),
    Uzbek("Uzbek", "Uzbek", "uz", "custom_icu"),
    Venda("Venda", "Venda", "ve", "custom_icu"),
    Vietnamese("Vietnamese", "Việt Nam", "vi", "custom_icu"),
    Volapuk("Volapuk", "Volapük", "vo", "custom_icu"),
    Waray("Waray", "Waray", "war", "custom_icu"),
    Welsh("Welsh", "Cymraeg", "cy", "custom_icu"),
    Wolof("Wolof", "Wolof", "wo", "custom_icu"),
    Xhosa("Xhosa", "isiXhosa", "xh", "custom_icu"),
    Yiddish("Yiddish", "ייִדיש", "yi", "custom_icu"),
    Yoruba("Yoruba", "Yoruba", "yo", "custom_icu"),
    Zhuang("Zhuang", "Zhuang", "za", "custom_icu"),
    Zulu("Zulu", "Zulu", "zu", "custom_icu"),
    Unknown("Unknown", "unknown", "unknown", null);



    private final String label;
    private final String nativeLabel;
    private final String langCode;
    private final String esAnalyzerName;
    private final boolean hidden;
    //Ordering of langauge based on usage on internet
    private final int usageOrdering;
    private final SupportedLanguage parentLanguage;
    private final String googleTranslateLangCode;

    private Float confidence;

    SupportedLanguage(String label, String nativeLabel, String langCode, String esAnalyzerName, int usageOrdering) {
        this(label, nativeLabel, langCode, null, esAnalyzerName, langCode, false, usageOrdering);
    }

    SupportedLanguage(String label, String nativeLabel, String langCode, String esAnalyzerName) {
        this(label, nativeLabel, langCode, null, esAnalyzerName, false);
    }

    SupportedLanguage(String label, String nativeLabel, String langCode, String esAnalyzerName, boolean hidden) {
        this(label, nativeLabel, langCode, null, esAnalyzerName, hidden);
    }

    SupportedLanguage(String label, String nativeLabel, String langCode, SupportedLanguage parentLanguage, String esAnalyzerName, boolean hidden) {
        this(label, nativeLabel, langCode, parentLanguage, esAnalyzerName, langCode, hidden, 1000);
    }

    SupportedLanguage(String label, String nativeLabel, String langCode, SupportedLanguage parentLanguage, String esAnalyzerName, String googleTranslateLangCode, boolean hidden) {
        this(label, nativeLabel, langCode, parentLanguage, esAnalyzerName, googleTranslateLangCode, hidden, 1000);
    }

    SupportedLanguage(String label, String nativeLabel, String langCode, SupportedLanguage parentLanguage, String esAnalyzerName,
                      String googleTranslateLangCode, boolean hidden,
                      int usageOrdering) {
        this.esAnalyzerName = esAnalyzerName;
        this.label = label;
        this.nativeLabel = nativeLabel;
        this.langCode =langCode;
        this.googleTranslateLangCode = googleTranslateLangCode;
        this.parentLanguage = parentLanguage;
        this.hidden = hidden;
        this.usageOrdering = usageOrdering;
    }

    public String getLangCode() {
        return langCode;
    }
}
