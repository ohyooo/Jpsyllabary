package com.ohyooo.common.viewmodel

import com.ohyooo.common.res.RR

object SingleViewModel {
    private var tvQueue = ArrayList<Int>()
    private var queueSize = 0
    private val dicts = arrayOf(
        "あ" to "a",
        "い" to "i",
        "う" to "u",
        "え" to "e",
        "お" to "o",
        "か" to "ka",
        "き" to "ki",
        "く" to "ku",
        "け" to "ke",
        "こ" to "ko",
        "さ" to "sa",
        "し" to "shi",
        "す" to "su",
        "せ" to "se",
        "そ" to "so",
        "た" to "ta",
        "ち" to "chi",
        "つ" to "tsu",
        "て" to "te",
        "と" to "to",
        "な" to "na",
        "に" to "ni",
        "ぬ" to "nu",
        "ね" to "ne",
        "の" to "no",
        "は" to "ha",
        "ひ" to "hi",
        "ふ" to "fu",
        "へ" to "he",
        "ほ" to "ho",
        "ま" to "ma",
        "み" to "mi",
        "む" to "mu",
        "め" to "me",
        "も" to "mo",
        "や" to "ya",
        "ゆ" to "yu",
        "よ" to "yo",
        "ら" to "ra",
        "り" to "ri",
        "る" to "ru",
        "れ" to "re",
        "ろ" to "ro",
        "わ" to "wa",
        "を" to "wo",
        "が" to "ga",
        "ぎ" to "gi",
        "ぐ" to "gu",
        "げ" to "ge",
        "ご" to "go",
        "ざ" to "za",
        "じ" to "zi",
        "ず" to "zu",
        "ぜ" to "ze",
        "ぞ" to "zo",
        "だ" to "da",
        "ぢ" to "zi",
        "づ" to "zu",
        "で" to "de",
        "ど" to "do",
        "ば" to "ba",
        "び" to "bi",
        "ぶ" to "bu",
        "べ" to "be",
        "ぼ" to "bo",
        "ぱ" to "pa",
        "ぴ" to "pi",
        "ぷ" to "pu",
        "ぺ" to "pe",
        "ぽ" to "po",
        "ア" to "a",
        "イ" to "i",
        "ウ" to "u",
        "エ" to "e",
        "オ" to "o",
        "カ" to "ka",
        "キ" to "ki",
        "ク" to "ku",
        "ケ" to "ke",
        "コ" to "ko",
        "サ" to "sa",
        "シ" to "shi",
        "ス" to "su",
        "セ" to "se",
        "ソ" to "so",
        "タ" to "ta",
        "チ" to "chi",
        "ッ" to "tsu",
        "テ" to "te",
        "ト" to "to",
        "ナ" to "na",
        "ニ" to "ni",
        "ヌ" to "nu",
        "ネ" to "ne",
        "ノ" to "no",
        "ハ" to "ha",
        "ヒ" to "hi",
        "フ" to "fu",
        "へ" to "he",
        "ホ" to "ho",
        "マ" to "ma",
        "ミ" to "mi",
        "ム" to "mu",
        "メ" to "me",
        "モ" to "mo",
        "ヤ" to "ya",
        "ユ" to "yu",
        "ヨ" to "yo",
        "ラ" to "ra",
        "リ" to "ri",
        "ル" to "ru",
        "レ" to "re",
        "ロ" to "ro",
        "ワ" to "wa",
        "ヲ" to "wo",
        "ガ" to "ga",
        "ギ" to "gi",
        "グ" to "gu",
        "ゲ" to "ge",
        "ゴ" to "go",
        "ザ" to "za",
        "ジ" to "zi",
        "ズ" to "zu",
        "ゼ" to "ze",
        "ゾ" to "zo",
        "ダ" to "da",
        "ヂ" to "zi",
        "ヅ" to "zu",
        "デ" to "de",
        "ド" to "do",
        "バ" to "ba",
        "ビ" to "bi",
        "ブ" to "bu",
        "ベ" to "be",
        "ボ" to "bo",
        "パ" to "pa",
        "ピ" to "pi",
        "プ" to "pu",
        "ペ" to "pe",
        "ポ" to "po",
    )
    private val count by lazy(LazyThreadSafetyMode.NONE) { dicts.size }

    private fun getType(name: Int) = when (name) {
        in 0..44 -> RR.strings.katakanaWithVoiceless
        in 45..89 -> RR.strings.hiraganaWithVoiceless
        in 90..109 -> RR.strings.katakanaWithVoicedSound
        in 110..129 -> RR.strings.hiraganaWithVoicedSound
        in 130..134 -> RR.strings.hiraganaWithSemiVoiced
        in 135..139 -> RR.strings.katakanaWithSemivoiced
        else -> RR.strings.empty
    }

    fun get(): SingleModel {
        val random = num
        val value = dicts[random]
        val kanaValue = value.first
        val pronValue = value.second
        val title = getType(random)
        return SingleModel(title, kanaValue, pronValue)
    }

    // 用队列
    // 生成随机数
    private val num: Int
        get() {
            var num: Int
            while (true) {
                num = (0 until count).random()
                if (tvQueue.size > 0) {
                    if (!tvQueue.contains(num)) {
                        tvQueue.add(num)
                        break
                    }
                } else {
                    tvQueue.add(num)
                    break
                }
            }
            if (tvQueue.size >= queueSize) {
                tvQueue.remove(0)
            }
            return num
        }
}

class SingleModel(
    val title: String,
    val kana: String,
    val pron: String,
)