package com.orangicsmarttechnology.orangic.Activity

import android.content.Context
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.view.MenuItem
import com.danielstone.materialaboutlibrary.ConvenienceBuilder
import com.danielstone.materialaboutlibrary.MaterialAboutActivity
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard
import com.danielstone.materialaboutlibrary.model.MaterialAboutList
import com.mikepenz.community_material_typeface_library.CommunityMaterial
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.orangicsmarttechnology.orangic.Constants
import com.orangicsmarttechnology.orangic.R

class AboutActivity : MaterialAboutActivity() {

    var colorIcon = R.color.mal_color_icon_light_theme

    override fun getActivityTitle(): CharSequence? {
        return "About"
    }

    override fun getMaterialAboutList(c: Context): MaterialAboutList {
        val appCardBuilder = MaterialAboutCard.Builder()

        appCardBuilder.addItem(MaterialAboutTitleItem.Builder()
                .text(Constants.APP_NAME)
                .desc(Constants.COMPANY_DESC)
                .icon(R.drawable.ic_orangic)
                .build())

        appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
                IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_info_outline)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "Version",
                false))

        appCardBuilder.addItem(MaterialAboutActionItem.Builder()
                .text("Privacy Policy")
                .icon(R.drawable.ic_https)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse(Constants.PRIVACY_POLICY_URL)))
                .build())

        /*appCardBuilder.addItem(MaterialAboutActionItem.Builder()
                .text("Open source libraries")
                .icon(IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18))
                .setOnClickAction {
                    LibsBuilder()
                            .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                            .withAutoDetect(true)
                            .withFields(R.string::class.java.fields)
                            .withAboutIconShown(true)
                            .withAboutVersionShown(true)
                            .start(this)
                }
                .build())*/

        val detailsCardBuilder = MaterialAboutCard.Builder()

        detailsCardBuilder.title("Details")

        /* detailsCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
                 IconicsDrawable(c)
                         .icon(CommunityMaterial.Icon.cmd_information_outline)
                         .color(ContextCompat.getColor(c, colorIcon))
                         .sizeDp(18),
                 "Version",
                 false))*/

        detailsCardBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
                IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_earth)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "Visit Website",
                true,
                Uri.parse(Constants.WEBSITE)))

        detailsCardBuilder.addItem(ConvenienceBuilder.createEmailItem(c,
                IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "Send an email",
                true,
                Constants.CONTACT_EMAIL,
                "Regarding Orangic App"))

        detailsCardBuilder.addItem(ConvenienceBuilder.createPhoneItem(c,
                IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_phone)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "Contact",
                true,
                Constants.CONTACT_NUMBER))

        detailsCardBuilder.addItem(ConvenienceBuilder.createMapItem(c,
                IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_map)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "Office",
                null,
                Constants.OFFICE_ADDRESS))

        val authorCardBuilder = MaterialAboutCard.Builder()
        authorCardBuilder.title("Designed By")

        authorCardBuilder.addItem(MaterialAboutActionItem.Builder()
                .text("Krishna Mandal")
                .subText("Krishna hackster")
                .icon(R.drawable.ic_perm_identity_black_18dp)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse(Constants.MY_PERSONAL_SITE)))
                .build())

        authorCardBuilder.addItem(MaterialAboutActionItem.Builder()
                .text("Guddu Kumar")
                .subText("Developer")
                .icon(R.drawable.ic_perm_identity_black_18dp)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://www.facebook.com/guddu.patel1")))
                .build())

        val supportCardBuilder = MaterialAboutCard.Builder()
        supportCardBuilder.title("Team")

        supportCardBuilder.addItem(MaterialAboutActionItem.Builder()
                .text("Rajan Singh")
                .subText("President at Nepal Press union Dhanusha")
                .icon(R.drawable.ic_perm_identity_black_18dp)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://www.facebook.com/rajan.singh.940")))
                .build())
        supportCardBuilder.addItem(MaterialAboutActionItem.Builder()
                .text("Bindeshwar Thakur")
                .subText("Poet")
                .icon(R.drawable.ic_perm_identity_black_18dp)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://www.facebook.com/tbindeshwar")))
                .build())
        supportCardBuilder.addItem(MaterialAboutActionItem.Builder()
                .text("Amar Mandal")
                .subText("Teacher")
                .icon(R.drawable.ic_perm_identity_black_18dp)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://www.facebook.com/profile.php?id=100004065472370")))
                .build())
        supportCardBuilder.addItem(MaterialAboutActionItem.Builder()
                .text("Kishor Mandal")
                .subText("Journalist")
                .icon(R.drawable.ic_perm_identity_black_18dp)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://www.facebook.com/kishor.mandal.52")))
                .build())
        supportCardBuilder.addItem(MaterialAboutActionItem.Builder()
                .text("Mukesh Mandal")
                .subText("Engineer")
                .icon(R.drawable.ic_perm_identity_black_18dp)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://www.facebook.com/profile.php?id=100010277539659")))
                .build())
        supportCardBuilder.addItem(MaterialAboutActionItem.Builder()
                .text("Aalok Adhikari")
                .subText("Student")
                .icon(R.drawable.ic_perm_identity_black_18dp)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://www.facebook.com/profile.php?id=aalok.kapar.372")))
                .build())

        return MaterialAboutList(appCardBuilder.build(), detailsCardBuilder.build(), authorCardBuilder.build(), supportCardBuilder.build())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                //NavUtils.navigateUpFromSameTask(this)
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
