package com.example.pet_booking_app.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_booking_app.R;
import com.example.pet_booking_app.models.Booking;
import com.example.pet_booking_app.models.Pet;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;


import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import com.example.pet_booking_app.R;
        import com.example.pet_booking_app.models.Pet;

        import org.jetbrains.annotations.NotNull;

        import java.util.List;
        import java.util.Random;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingViewHolder> {

    private int[] dogImages = {R.drawable.dog1, R.drawable.dog2, R.drawable.dog3, R.drawable.dog4};
    private int[] catImages = {R.drawable.cat1, R.drawable.cat4};
    private List<Booking> bookings;

    public BookingsAdapter(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @NotNull
    @Override
    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_bookings, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull BookingViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        holder.petName.setText(booking.getPetName());

        Log.d("bookingspetname", "onBindViewHolder: ");

//        int imageResource;
//        if (booking.getType().equalsIgnoreCase("dog")) {
//            int randomIndex = new Random().nextInt(dogImages.length);
//            imageResource = dogImages[randomIndex];
//        } else if (booking.getType().equalsIgnoreCase("cat")) {
//            int randomIndex = new Random().nextInt(catImages.length);
//            imageResource = catImages[randomIndex];
//        } else {
//            // Set a default image resource if the pet type is not recognized
//            imageResource = R.drawable.default_pet;
//        }

//        holder.petImage.setImageResource(imageResource);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        ImageView petImage;
        TextView petName;

        public BookingViewHolder(View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.pet_img);
            petName = itemView.findViewById(R.id.pet_name);
        }
    }
}