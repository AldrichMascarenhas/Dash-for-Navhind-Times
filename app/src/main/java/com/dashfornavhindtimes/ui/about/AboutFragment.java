package com.dashfornavhindtimes.ui.about;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.BuildConfig;
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
    }


    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {

        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text(R.string.app_name_full)
                .icon(R.mipmap.ic_launcher)
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.about_version)
                .subText(com.dashfornavhindtimes.BuildConfig.VERSION_NAME)
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_info_outline)
                        .sizeDp(18))
                .build());

        //TODO : Fix wrong package
        appCardBuilder.addItem(ConvenienceBuilder.createRateActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_star)
                        .sizeDp(18),
                getString(R.string.about_rate),
                null
        ));


        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.about_releases)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_rocket)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, getString(R.string.about_releases), getString(R.string.about_releases_link), true, false))

                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.about_legal)
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_gavel)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, getString(R.string.about_legal), getString(R.string.about_legal_link), true, false))

                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.about_license)
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, getString(R.string.about_license), getString(R.string.about_license_link), true, false))

                .build());

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title(R.string.about_author);

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.about_author_name)
                .subText(R.string.about_author_company)
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_person)
                        .sizeDp(18))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.about_fork_on_github)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse(getString(R.string.about_fork_on_github_link))))
                .build());


        authorCardBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_earth)
                        .sizeDp(18),
                getString(R.string.about_visit_website),
                true,
                Uri.parse(getString(R.string.about_visit_website_link))));

        authorCardBuilder.addItem(ConvenienceBuilder.createEmailItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .sizeDp(18),
                getString(R.string.about_send_email),
                true,
                getString(R.string.about_send_email_address),
                getString(R.string.about_send_email_subject)));


        MaterialAboutCard.Builder legalCardBuilder = new MaterialAboutCard.Builder();
        legalCardBuilder.title(R.string.about_disclaimer);


        legalCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.about_disclaimer_text_header)
                .subText(R.string.about_disclaimer_text_subtext)
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
