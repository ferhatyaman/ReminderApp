package com.example.reminderapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.RadioGroup;
import android.support.v7.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements EventAdapter.EventHandler {

    private List<Event> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private Event event;
    private Date d;

    public void loadData() {

        try {
            d = new SimpleDateFormat("dd/MM/yyyy").parse("06/04/2019");
            event = new Event("CS310 Midterm1", "Midterm at LO65 from 9am", d, 4, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("07/04/2019");
            event = new Event("Room meeting", "Meet Mr. Mbape before things get worse", d, 4, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("06/05/2019");
            event = new Event("CS310 Midterm1 Objection", "Room LO65 from 10am ", d, 3, false);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("09/04/2019");
            event = new Event("CS308 Project presentation", "4th sprint user-inteface design", d, 4, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("31/03/2019");
            event = new Event("Judiy's Funeral", "buy flowers", d, 3, false);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("02/04/2019");
            event = new Event("Avengers release", "Invite roommates to watch it", d, 2, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2019");
            event = new Event("Job application", "Ware the black suit", d, 4, false);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("06/04/2019");
            event = new Event("Google's interview", "Focus on software engineering nothing else matters", d, 1, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("06/04/2019");
            event = new Event("Summer school starts", "Nothing special", d, 4, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("08/05/2019");
            event = new Event("MS application deadline", "now or never", d, 4, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("29/03/2019");
            event = new Event("Make plane reservation", "Most preferably Turkish Airlines", d, 3, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("06/04/2019");
            event = new Event("Trip to Africa", "Dont miss your plane", d, 4, false);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("06/05/2019");
            event = new Event("See the dentist", "Midterm at LO65 from 9am", d, 2, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("04/04/2019");
            event = new Event("CS310 Midterm2", "Midterm at LO65 from 9am", d, 4, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("06/04/2019");
            event = new Event("Call Dad", "Remind him of fees", d, 3, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("06/05/2019");
            event = new Event("Shopping", "With loved ones", d, 1, false);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2019");
            event = new Event("fundraiser", "Give whatever you have", d, 4, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("28/05/2019");
            event = new Event("CS310 Final", "All topics includes", d, 1, true);
            eventList.add(event);

            d = new SimpleDateFormat("dd/MM/yyyy").parse("06/04/2019");
            event = new Event("Date with Juliet", "Hilton hotel, Mecidiyekoy", d, 4, false);
            eventList.add(event);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        recyclerView = findViewById(R.id.myList);
        RadioGroup radioGroup = findViewById(R.id.sort);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventAdapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(eventAdapter);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.sortPriority:
                        Collections.sort(eventList, new PriorityComparator());
                        updateUI();
                        break;
                    case R.id.sortDate:
                        Collections.sort(eventList, new DateComparator());
                        updateUI();
                        break;
                }
            }
        });
        }

        public void updateUI() {
            eventAdapter.notifyDataSetChanged();
        }

        private class PriorityComparator implements Comparator<Event> {

            public int compare(Event event1, Event event2) {
                return Integer.valueOf(event2.getPriority()).compareTo(event1.getPriority());
            }
        }

        private class DateComparator implements Comparator<Event> {

            public int compare(Event event1, Event event2) {
                return event1.getDate().compareTo(event2.getDate());
            }
        }

        @Override
        public void onItemClick(int index) {
            updateUI();
        }
    }
