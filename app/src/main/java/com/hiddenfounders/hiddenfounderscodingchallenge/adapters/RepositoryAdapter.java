package com.hiddenfounders.hiddenfounderscodingchallenge.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hiddenfounders.hiddenfounderscodingchallenge.R;
import com.hiddenfounders.hiddenfounderscodingchallenge.models.Repository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Redaa on 12/18/2017.
 */

public class RepositoryAdapter extends ArrayAdapter<Repository> {
    private Context context;
    private List<Repository> repositories;
    private int resource;

    public RepositoryAdapter(@NonNull Context context, @LayoutRes int resource,
                             @NonNull List<Repository> objects) {
        super(context, resource, objects);
        this.context = context;
        this.repositories = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //ViewHolder to hold our view since the list will be long
        ViewHolder holder;
        Repository repository = repositories.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(this.resource, parent, false);
            holder = new ViewHolder();
            holder.repository_name = convertView.findViewById(R.id.repository_name);
            holder.repository_description = convertView.findViewById(R.id.repository_description);
            holder.avatar = convertView.findViewById(R.id.avatar_image_view);
            holder.repository_stars = convertView.findViewById(R.id.repository_stars);
            holder.repository_username = convertView.findViewById(R.id.repository_username);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.repository_name.setText(repository.getName());
        holder.repository_username.setText(repository.getOwner().getLogin());
        holder.repository_stars.setText(repository.getStargazers_count() + ""); //to cast int to string
        holder.repository_description.setText(repository.getDescription());
        Picasso.with(context).load(repository.getOwner().getAvatar_url())
                .error(R.drawable.ic_cloud_off_black_24dp).into(holder.avatar);
        return convertView;
    }

    @Override
    public int getCount() {
        if (repositories != null)
            return repositories.size();
        return 0;
    }

    @Nullable
    @Override
    public Repository getItem(int position) {
        if (repositories != null)
            return repositories.get(position);
        return null;
    }

    private class ViewHolder {
        TextView repository_name;
        TextView repository_description;
        TextView repository_stars;
        TextView repository_username;
        ImageView avatar;
    }
}
