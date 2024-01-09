package com.example.pet_booking_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_booking_app.R;
import com.example.pet_booking_app.models.Pet;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private int[] dogImages = {R.drawable.dog1, R.drawable.dog2, R.drawable.dog3, R.drawable.dog4};
    private int[] catImages = {R.drawable.cat1, R.drawable.cat4};
    private List<Pet> pets;

    public PetAdapter(List<Pet> pets) {
        this.pets = pets;
    }

    @NotNull
    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull PetViewHolder holder, int position) {
        Pet pet = pets.get(position);
        holder.petName.setText(pet.getName());

        int imageResource;
        if (pet.getType().equalsIgnoreCase("dog")) {
            int randomIndex = new Random().nextInt(dogImages.length);
            imageResource = dogImages[randomIndex];
        } else if (pet.getType().equalsIgnoreCase("cat")) {
            int randomIndex = new Random().nextInt(catImages.length);
            imageResource = catImages[randomIndex];
        } else {
            // Set a default image resource if the pet type is not recognized
            imageResource = R.drawable.default_pet;
        }

        holder.petImage.setImageResource(imageResource);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        ImageView petImage;
        TextView petName;

        public PetViewHolder(View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.petImage);
            petName = itemView.findViewById(R.id.petName);
        }
    }
}