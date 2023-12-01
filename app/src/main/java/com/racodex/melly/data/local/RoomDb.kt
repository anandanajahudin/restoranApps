package com.racodex.melly.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.racodex.melly.R
import com.racodex.melly.R.drawable.user
import com.racodex.melly.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [
        Advertisement::class,
        Manufacturer::class,
        Review::class,
        User::class,
        PaymentProvider::class,
        UserPaymentProvider::class,
        Product::class,
        BookmarkItem::class,
        Location::class,
        CartItem::class,
        Order::class,
        OrderItem::class,
        OrderPayment::class,
        Notification::class,
        ProductColor::class,
        ProductSize::class,
    ],
    version = 1, exportSchema = false)
abstract class RoomDb : RoomDatabase() {

    /** A function that used to retrieve Room's related dao instance */
    abstract fun getDao(): RoomDao

    class PopulateDataClass @Inject constructor(
        private val client: Provider<RoomDb>,
        private val scope: CoroutineScope,
    ) : Callback() {
        private val description =
            "This is the description text that is supposed to be long enough to show how the UI looks, so it's not a real text.\n"
        private val manufacturers = listOf(
            Manufacturer(id = 1, name = "Burger", icon = R.drawable.burger_beef_keju),
            Manufacturer(id = 2, name = "Nasi", icon = R.drawable.nasgor),
            Manufacturer(id = 3, name = "Mie", icon = R.drawable.mie_goreng_udang),
        )
        private val advertisements = listOf(
            Advertisement(1, R.drawable.banner1, 1, 0),
            Advertisement(2, R.drawable.banner2, 2, 0),
            Advertisement(3, R.drawable.banner3, 3, 0),
        )

        private val burgerProducts = listOf(
            Product(
                id = 1,
                name = "Burger Beef",
                image = R.drawable.burger,
                price = 8.0,
                description = description,
                manufacturerId = 1,
                basicColorName = "dark-green",
            ),
            Product(
                id = 2,
                name = "Burger Ayam Fillet",
                image = R.drawable.burger_ayam_fillet,
                price = 7.0,
                description = description,
                manufacturerId = 1,
                basicColorName = "gold",
            ),
            Product(
                id = 3,
                name = "Burger Beef Paket 1",
                image = R.drawable.burger_beef_double_keju_kentang_esteh,
                price = 20.0,
                description = description,
                manufacturerId = 1,
                basicColorName = "black",
            ),
            Product(
                id = 4,
                name = "Burger Beef Double + Kentang",
                image = R.drawable.burger_beef_double_kentang,
                price = 18.0,
                description = description,
                manufacturerId = 1,
                basicColorName = "black",
            ),
        )

        private val nasiProducts = listOf(
            Product(
                id = 5,
                name = "Nasi Goreng",
                image = R.drawable.nasgor,
                price = 8.0,
                description = description,
                manufacturerId = 2,
                basicColorName = "green",
            ),
            Product(
                id = 6,
                name = "Nasi Goreng Spesial Telur",
                image = R.drawable.nasgor_telur,
                price = 9.0,
                description = description,
                manufacturerId = 2,
                basicColorName = "gray",
            ),
        )

        private val mieProducts = listOf(
            Product(
                id = 7,
                name = "Mie Goreng Spesial",
                image = R.drawable.mie_goreng_spesial,
                price = 9.0,
                description = description,
                manufacturerId = 3,
                basicColorName = "gray",
            ),
            Product(
                id = 8,
                name = "Mie Goreng Udang",
                image = R.drawable.mie_goreng_udang,
                price = 11.0,
                description = description,
                manufacturerId = 3,
                basicColorName = "gray",
            ),
        )

        private val paymentProviders = listOf(
            PaymentProvider(
                id = "apple",
                title = R.string.apple_pay,
                icon = R.drawable.ic_apple,
            ),
            PaymentProvider(
                id = "master",
                title = R.string.master_card,
                icon = R.drawable.ic_master_card,
            ),
            PaymentProvider(
                id = "visa",
                title = R.string.visa,
                icon = R.drawable.ic_visa,
            ),
            PaymentProvider(
                id = "cod",
                title = R.string.cod,
                icon = R.drawable.ic_nike,
            ),
        )
        private val userPaymentAccounts = listOf(
            UserPaymentProvider(
                providerId = "apple",
                cardNumber = "8402-5739-2039-5784"
            ),
            UserPaymentProvider(
                providerId = "master",
                cardNumber = "3323-8202-4748-2009"
            ),
            UserPaymentProvider(
                providerId = "visa",
                cardNumber = "7483-02836-4839-2833"
            ),
            UserPaymentProvider(
                providerId = "cod",
                cardNumber = ""
            ),
        )
        private val userLocation = Location(
            address = "Pandaan",
            city = "Pandaan",
            country = "Indonesia",
        )

        init {
            burgerProducts.onEach {
                it.sizes = mutableListOf(
                    ProductSize(it.id, 38),
                    ProductSize(it.id, 40),
                    ProductSize(it.id, 42),
                    ProductSize(it.id, 44),
                )
            }
            nasiProducts.onEach {
                it.sizes = mutableListOf(
                    ProductSize(it.id, 38),
                    ProductSize(it.id, 40),
                    ProductSize(it.id, 42),
                    ProductSize(it.id, 44),
                )
            }
            scope.launch {
                populateDatabase(dao = client.get().getDao(), scope = scope)
            }
        }

        private suspend fun populateDatabase(dao: RoomDao, scope: CoroutineScope) {
            /** Save users */
            scope.launch {
                dao.saveUser(
                    User(
                        userId = 1,
                        name = "User",
                        profile = user,
                        phone = "+6285821364004",
                        email = "example@gmail.com",
                        password = "12345678",
                        token = "ds2f434ls2ks2lsj2ls",
                    )
                )
            }
            /** insert manufacturers */
            scope.launch {
                manufacturers.forEach {
                    dao.insertManufacturer(it)
                }
            }
            /** insert advertisements */
            scope.launch {
                advertisements.forEach {
                    dao.insertAdvertisement(it)
                }
            }

            /** Insert products */
            // Insert Product Burger
            scope.launch {
                burgerProducts.plus(nasiProducts).forEach {
                    /** Insert the product itself */
                    dao.insertProduct(product = it)
                    /** Insert colors */
                    it.colors?.forEach { productColor ->
                        dao.insertOtherProductCopy(productColor)
                    }
                    /** Insert size */
                    it.sizes?.forEach { productSize ->
                        dao.insertSize(productSize)
                    }
                }
            }

            // Insert Product Nasi
//            scope.launch {
//                nasiProducts.forEach {
//                    /** Insert the product itself */
//                    dao.insertProduct(product = it)
//                    /** Insert colors */
//                    it.colors?.forEach { productColor ->
//                        dao.insertOtherProductCopy(productColor)
//                    }
//                    /** Insert size */
//                    it.sizes?.forEach { productSize ->
//                        dao.insertSize(productSize)
//                    }
//                }
//            }

            // Insert Product Mie
//            scope.launch {
//                mieProducts.forEach {
//                    /** Insert the product itself */
//                    dao.insertProduct(product = it)
//                    /** Insert colors */
//                    it.colors?.forEach { productColor ->
//                        dao.insertOtherProductCopy(productColor)
//                    }
//                    /** Insert size */
//                    it.sizes?.forEach { productSize ->
//                        dao.insertSize(productSize)
//                    }
//                }
//            }

            /** Insert payment providers */
            scope.launch {
                paymentProviders.forEach {
                    dao.savePaymentProvider(paymentProvider = it)
                }
            }

            /** Insert user's payment providers */
            scope.launch {
                userPaymentAccounts.forEach {
                    dao.saveUserPaymentProvider(it)
                }
            }
            /** Insert user's location */
            scope.launch {
                dao.saveLocation(location = userLocation)
            }
        }
    }

}