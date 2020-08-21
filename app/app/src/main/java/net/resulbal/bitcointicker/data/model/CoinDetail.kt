package net.resulbal.bitcointicker.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import net.resulbal.bitcointicker.ui.base.BaseState

/**
 * Created by rslbl on 2020-08-21.
 */

@Parcelize
data class CoinDetail(
  @field:SerializedName("id") val id: String? = null,
  @field:SerializedName("symbol") val symbol: String? = null,
  @field:SerializedName("name") val name: String? = null,
  @field:SerializedName("hashing_algorithm") val hashAlgorithm: String? = null,
  @field:SerializedName("description") val description: CoinDescription? = null,
  @field:SerializedName("image") val image: Image? = null,
  @field:SerializedName("market_data") val marketData: MarketData? = null,
): BaseState

@Parcelize
data class CoinDescription(
  @field:SerializedName("id") val id: String? = null,
  @field:SerializedName("en") val en: String? = null,
  @field:SerializedName("de") val de: String? = null,
  @field:SerializedName("es") val es: String? = null,
  @field:SerializedName("fr") val fr: String? = null,
  @field:SerializedName("it") val it: String? = null,
  @field:SerializedName("pl") val pl: String? = null,
  @field:SerializedName("ro") val ro: String? = null,
  @field:SerializedName("hu") val hu: String? = null,
  @field:SerializedName("nl") val nl: String? = null,
  @field:SerializedName("pt") val pt: String? = null,
  @field:SerializedName("sv") val sv: String? = null,
  @field:SerializedName("vi") val vi: String? = null,
  @field:SerializedName("tr") val tr: String? = null,
  @field:SerializedName("ru") val ru: String? = null,
  @field:SerializedName("ja") val ja: String? = null,
  @field:SerializedName("zh") val zh: String? = null,
  @field:SerializedName("zh-tw") val zhTw: String? = null,
  @field:SerializedName("ko") val ko: String? = null,
  @field:SerializedName("ar") val ar: String? = null,
  @field:SerializedName("th") val th: String? = null
): BaseState

@Parcelize
data class Image(
  @field:SerializedName("thumb") val thumb: String? = null,
  @field:SerializedName("small") val small: String? = null,
  @field:SerializedName("large") val large: String? = null
): BaseState

@Parcelize
data class MarketData(
  @field:SerializedName("current_price") val currentPrice: CurrentPrice? = null,
  @field:SerializedName("price_change_percentage_24h") val changePercentage: Float? = null
): BaseState

@Parcelize
data class CurrentPrice(
  @field:SerializedName("aed") val aed: Float? = null,
  @field:SerializedName("ars") val ars: Float? = null,
  @field:SerializedName("aud") val aud: Float? = null,
  @field:SerializedName("bch") val bch: Float? = null,
  @field:SerializedName("bdt") val bdt: Float? = null,
  @field:SerializedName("bhd") val bhd: Float? = null,
  @field:SerializedName("bmd") val bmd: Float? = null,
  @field:SerializedName("bnb") val bnb: Float? = null,
  @field:SerializedName("brl") val brl: Float? = null,
  @field:SerializedName("btc") val btc: Float? = null,
  @field:SerializedName("cad") val cad: Float? = null,
  @field:SerializedName("chf") val chf: Float? = null,
  @field:SerializedName("clp") val clp: Float? = null,
  @field:SerializedName("cny") val cny: Float? = null,
  @field:SerializedName("czk") val czk: Float? = null,
  @field:SerializedName("dkk") val dkk: Float? = null,
  @field:SerializedName("eos") val eos: Float? = null,
  @field:SerializedName("eth") val eth: Float? = null,
  @field:SerializedName("eur") val eur: Float? = null,
  @field:SerializedName("gbp") val gbp: Float? = null,
  @field:SerializedName("hkd") val hkd: Float? = null,
  @field:SerializedName("huf") val huf: Float? = null,
  @field:SerializedName("idr") val idr: Float? = null,
  @field:SerializedName("ils") val ils: Float? = null,
  @field:SerializedName("inr") val inr: Float? = null,
  @field:SerializedName("jpy") val jpy: Float? = null,
  @field:SerializedName("krw") val krw: Float? = null,
  @field:SerializedName("kwd") val kwd: Float? = null,
  @field:SerializedName("lkr") val lkr: Float? = null,
  @field:SerializedName("ltc") val ltc: Float? = null,
  @field:SerializedName("mmk") val mmk: Float? = null,
  @field:SerializedName("mxn") val mxn: Float? = null,
  @field:SerializedName("myr") val myr: Float? = null,
  @field:SerializedName("nok") val nok: Float? = null,
  @field:SerializedName("nzd") val nzd: Float? = null,
  @field:SerializedName("php") val php: Float? = null,
  @field:SerializedName("pkr") val pkr: Float? = null,
  @field:SerializedName("pln") val pln: Float? = null,
  @field:SerializedName("rub") val rub: Float? = null,
  @field:SerializedName("sar") val sar: Float? = null,
  @field:SerializedName("sek") val sek: Float? = null,
  @field:SerializedName("sgd") val sgd: Float? = null,
  @field:SerializedName("thb") val thb: Float? = null,
  @field:SerializedName("try") val tr: Float? = null,
  @field:SerializedName("twd") val twd: Float? = null,
  @field:SerializedName("uah") val uah: Float? = null,
  @field:SerializedName("usd") val usd: Float? = null,
  @field:SerializedName("vef") val vef: Float? = null,
  @field:SerializedName("vnd") val vnd: Float? = null,
  @field:SerializedName("xag") val xag: Float? = null,
  @field:SerializedName("xau") val xau: Float? = null,
  @field:SerializedName("xdr") val xdr: Float? = null,
  @field:SerializedName("xlm") val xlm: Float? = null,
  @field:SerializedName("xrp") val xrp: Float? = null,
  @field:SerializedName("zar") val zar: Float? = null,
  @field:SerializedName("link") var link: Float? = null
): BaseState