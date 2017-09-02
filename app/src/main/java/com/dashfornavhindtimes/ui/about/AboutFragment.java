package com.dashfornavhindtimes.ui.about;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.dashfornavhindtimes.R;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class AboutFragment extends MaterialAboutFragment {

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {

        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

        // Add items to card

        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text("Dash for Navhind Times")
                .icon(R.mipmap.ic_launcher)
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Version")
                .subText("1.0.0")
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_info_outline)
                        .sizeDp(18))
                .build());

        appCardBuilder.addItem(ConvenienceBuilder.createRateActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_star)
                        .sizeDp(18),
                "Rate this app",
                null
        ));



        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Releases")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_rocket)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, "Releases", "https://github.com/AldrichMascarenhas/Dash-for-Navhind-Times/releases", true, false))

                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Legal")
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_gavel)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, "Legal", "https://github.com/AldrichMascarenhas/Dash-for-Navhind-Times/blob/master/LEGAL.md", true, false))

                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("License")
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, "License", "https://github.com/AldrichMascarenhas/Dash-for-Navhind-Times/blob/master/LICENSE.md/LICENSE.md", true, false))

                .build());

        ////////

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title("Author");

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Aldrich Mascarenhas")
                .subText("NerdCutlet Productions")
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_person)
                        .sizeDp(18))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Fork on GitHub")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://github.com/AldrichMascarenhas/Dash-for-Navhind-Times")))
                .build());


        authorCardBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_earth)
                        .sizeDp(18),
                "Visit Website",
                true,
                Uri.parse("http://aldrichmascarenhas.com/")));

        authorCardBuilder.addItem(ConvenienceBuilder.createEmailItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .sizeDp(18),
                "Send an email",
                true,
                "nerdcutletproductions@gmail.com",
                "Question concerning Dash for Navhind Times"));

        /////

        MaterialAboutCard.Builder legalCardBuilder = new MaterialAboutCard.Builder();
        legalCardBuilder.title("Disclaimer");


        legalCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Please Note")
                .subText("This App is not the Official Navhind Times App")
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_person)
                        .sizeDp(18))
                .build());



        return new MaterialAboutList(appCardBuilder.build(), authorCardBuilder.build(),legalCardBuilder.build() );




    }

    @Override
    protected int getTheme() {
        return R.style.AppTheme_MaterialAboutFragment;
    }


}
