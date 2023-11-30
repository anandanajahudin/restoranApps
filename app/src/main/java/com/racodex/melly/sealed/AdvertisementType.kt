package com.racodex.melly.sealed

sealed class AdvertisementType{
    object Store: AdvertisementType()
    object Product: AdvertisementType()
}