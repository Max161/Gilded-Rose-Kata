package com.gildedrose

private const val MAX_QUALITY = 50
private const val MIN_QUALITY = 0
private const val AGED_BRIE = "Aged Brie"
private const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
private const val SULFURAS = "Sulfuras, Hand of Ragnaros"

class GildedRose(val items: List<Item>) {


    fun updateQuality() {
        for (item in items) {
            handleSellIn(item)
        }
    }

    private fun manageBackstageQuality(item: Item) {
        if (item.sellIn < 11) {
            increaseQuality(item)
        }
        if (item.sellIn < 6) {
            increaseQuality(item)
        }
        increaseQuality(item)
    }

    private fun manageExpiredItems(item: Item) =
        when (item.name) {
            BACKSTAGE_PASSES -> setMinQuality(item)
            AGED_BRIE -> increaseQuality(item)
            SULFURAS -> Unit
            else -> decreaseQuality(item)
        }

    private fun handleSellIn(item: Item) {
        when (item.name) {
            AGED_BRIE -> increaseQuality(item)
            BACKSTAGE_PASSES -> manageBackstageQuality(item)
            SULFURAS -> Unit
            else -> decreaseQuality(item)
        }

        if (item.name != SULFURAS) {
            decreaseSellIn(item)
        }

        if (item.sellIn < 0) {
            manageExpiredItems(item)
        }
    }

    fun decreaseQuality(item: Item) {
        if (item.quality > MIN_QUALITY) {
            item.quality--
        }
    }

    fun increaseQuality(item: Item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++
        }
    }

    private fun setMinQuality(item: Item) {
        item.quality = MIN_QUALITY
    }

    private fun decreaseSellIn(item: Item) {
        item.sellIn = item.sellIn - 1
    }

}

