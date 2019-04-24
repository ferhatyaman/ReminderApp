package com.example.reminderapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder>
{

    private EventHandler eventHandler;
    private List<Event> eventlist;

    public EventAdapter(EventHandler eventHandler, List<Event> eventlist) {
        this.eventHandler = eventHandler;
        this.eventlist = eventlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemWiev = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event, viewGroup, false);
        return new MyViewHolder(itemWiev);
    }

    @Override // set values on views
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        myViewHolder.priorityLevel.setOnSeekBarChangeListener(null);
        myViewHolder.enable.setOnCheckedChangeListener(null);


        myViewHolder.accountimage.setImageResource(R.mipmap.ic_launcher_account);
        myViewHolder.taskTitle.setText(eventlist.get(i).getTitle());
        myViewHolder.taskDescription.setText(eventlist.get(i).getNote());
        myViewHolder.taskDueDate.setText(getDateString(eventlist.get(i).getDate()));
        myViewHolder.priorityLevel.setProgress(eventlist.get(i).getPriority());
        myViewHolder.enable.setChecked(eventlist.get(i).getStatus());
        myViewHolder.delete.setImageResource(R.drawable.ic_cancel_red_24dp);

        final int j = i;
        myViewHolder.priorityLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int newPriority = myViewHolder.priorityLevel.getProgress();
                eventlist.get(j).setPriority(newPriority);
                eventHandler.onItemClick(j);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


        myViewHolder.enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Boolean newStatus = myViewHolder.enable.isChecked();
                eventlist.get(j).setStatus(newStatus);
                eventHandler.onItemClick(j);
            }
        });


        myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventlist.remove(j);
                eventHandler.onItemClick(j);

            }

        });
    }

    private String getDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM");
        return dateFormat.format(date);
    }

    @Override
    public int getItemCount() {
        return eventlist.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView accountimage;
        private TextView taskTitle;
        private TextView taskDescription;
        private TextView taskDueDate;
        private SeekBar priorityLevel;
        private Switch enable;
        private ImageButton delete;

        public MyViewHolder(View rowView) {
            super(rowView);

            accountimage = rowView.findViewById(R.id.accountImage);
            taskTitle = rowView.findViewById(R.id.taskTitle);
            taskDescription = rowView.findViewById(R.id.taskDescription);
            taskDueDate = rowView.findViewById(R.id.taskDueDate);
            priorityLevel = rowView.findViewById(R.id.priorityLevel);
            enable = rowView.findViewById(R.id.enable);
            delete = rowView.findViewById(R.id.delete);
            priorityLevel.setMax(4);
        }
    }

    interface EventHandler{
        void onItemClick(int index);
    }
}
