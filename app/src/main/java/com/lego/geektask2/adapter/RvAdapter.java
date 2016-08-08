package com.lego.geektask2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lego.geektask2.R;
import com.lego.geektask2.Utils.Person;

import java.util.List;

/**
 * @author Lego on 08.08.2016.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.PersonViewHolder> {

    List<Person> persons;

    public RvAdapter(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pattern, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.personTitle.setText(persons.get(position).title);
        holder.personFirstName.setHint(persons.get(position).labelOne);

        holder.personLastName.setHint(persons.get(position).labelTwo);

        holder.personEmail.setHint(persons.get(position).labelTree);

        holder.personPhone.setHint(persons.get(position).labelFour);
        holder.personFirstName.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.profile, 0, 0, 0);
        if (position>0){
            holder.personLastName.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.date, 0, 0, 0);
            holder.personEmail.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.profile, 0, 0, 0);
            holder.personPhone.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.password, 0, 0, 0);
        }else {
            holder.personLastName.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.profile, 0, 0, 0);
            holder.personEmail.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.email, 0, 0, 0);
            holder.personPhone.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.phone, 0, 0, 0);
        }

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView personTitle;
        EditText personFirstName;
        EditText personLastName;
        EditText personEmail;
        EditText personPhone;

        PersonViewHolder(View itemView) {
            super(itemView);
            personTitle = (TextView) itemView.findViewById(R.id.textViewRegistrationTitle);
            personFirstName = (EditText) itemView.findViewById(R.id.input_registration_firstname);
            personLastName = (EditText) itemView.findViewById(R.id.input_registration_lastname);
            personEmail = (EditText) itemView.findViewById(R.id.input_registration_email);
            personPhone = (EditText) itemView.findViewById(R.id.input_registration_phone);
        }
    }
}

