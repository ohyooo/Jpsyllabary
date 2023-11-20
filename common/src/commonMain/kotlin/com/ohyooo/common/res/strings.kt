package com.ohyooo.common.res

object RR {
    val strings: Strings
        get() = ZHStrings
}

interface Strings {
    val application_name: String

    val katakanaWithVoiceless: String
    val hiraganaWithVoiceless: String
    val katakanaWithVoicedSound: String
    val hiraganaWithVoicedSound: String
    val hiraganaWithSemiVoiced: String
    val katakanaWithSemivoiced: String

    val katakana: String
    val hiragana: String
    val romaji: String
    val sonant: String

    val empty: String

    val single: String
    val table: String

    val twister: String
    val source_code: String
}

private object ZHStrings : Strings {
    override val application_name = "五十音测试"

    override val katakanaWithVoiceless = "片假名 · 清音"
    override val hiraganaWithVoiceless = "平假名 · 清音"
    override val katakanaWithVoicedSound = "片假名 · 浊音"
    override val hiraganaWithVoicedSound = "平假名 · 浊音"
    override val hiraganaWithSemiVoiced = "平假名 · 半浊音"
    override val katakanaWithSemivoiced = "片假名 · 半浊音"

    override val katakana = "片假名"
    override val hiragana = "平假名"
    override val romaji = "罗马音"
    override val sonant = "浊音"

    override val empty = ""

    override val single = "快测"
    override val table = "表格"

    override val twister = "绕口令"
    override val source_code = "源码"
}

private object ENStrings : Strings {
    override val application_name = "JpSyllabary"

    override val katakanaWithVoiceless = "Katakana · Voiceless"
    override val hiraganaWithVoiceless = "Hiragana · Voiceless"
    override val katakanaWithVoicedSound = "Katakana · Voiced Sound"
    override val hiraganaWithVoicedSound = "Hiragana · Voiced Sound"
    override val hiraganaWithSemiVoiced = "Hiragana · Semi-Voiced"
    override val katakanaWithSemivoiced = "Katakana · Semi-voiced"

    override val katakana = "Katakana"
    override val hiragana = "Hiragana"
    override val romaji = "Romaji"
    override val sonant = "Sonant"

    override val empty = ""

    override val single = "Single"
    override val table = "Table"

    override val twister = "Tongue Twister"
    override val source_code = "Source Code"

}