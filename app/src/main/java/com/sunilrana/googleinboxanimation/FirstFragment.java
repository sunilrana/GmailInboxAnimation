package com.sunilrana.googleinboxanimation;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.Arrays;


public class FirstFragment extends Fragment implements OnItemClickListener{

    private FastOutSlowInInterpolator transitionInterpolator = new FastOutSlowInInterpolator();
    private long TRANSITION_DURATION = 800L;
    private String TAP_POSITION = "tap_position";

    private int tapPosition = RecyclerView.NO_POSITION;
    Rect viewRect = new Rect();

    RecyclerView recyclerView;


    public ArrayList<String> getData()
    {
        return  new ArrayList<String>(Arrays.asList("111", "222", "333" ,"444", "ABC", "DEF", "GEH", "IJK", "LMN", "OPQ", "RST", "UVW", "XYZ"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postponeEnterTransition();
        recyclerView = (RecyclerView)view.findViewById(R.id.emailList);
        view.findViewById(R.id.progressBar).setVisibility(View.GONE);
        RecylerAdapter adapter = new RecylerAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);


        adapter.setData(getData());
        setViewDrawListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    private void setViewDrawListener(){
        if (getView().getParent() instanceof ViewGroup){
            ((ViewGroup) getView().getParent()).getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (getEnterTransition() == null) {
                        SlideExplode slideExplode = new SlideExplode();
                        slideExplode.setDuration(TRANSITION_DURATION)
                                .setInterpolator(transitionInterpolator);

                        setExitTransition(slideExplode);
                    }
                    View recylerViewitem = recyclerView.getLayoutManager().findViewByPosition(tapPosition);
                    if(recylerViewitem != null)
                        recylerViewitem.getGlobalVisibleRect(viewRect);

                    ((Transition)getExitTransition()).setEpicenterCallback(new Transition.EpicenterCallback() {
                        @Override
                        public Rect onGetEpicenter(Transition transition) {
                            return viewRect;
                        }
                    });
                    startPostponedEnterTransition();
                    return true;
                }
            });
        }
    }


    @Override
    public void onItemClick(View view, int position) {
        tapPosition = position;
        addDetailFragment(view);
    }

    public void addDetailFragment(View sharedView) {

        sharedView.getGlobalVisibleRect(viewRect);
        if(getExitTransition() != null) {
            ((Transition) getExitTransition()).setEpicenterCallback(new Transition.EpicenterCallback() {
                @Override
                public Rect onGetEpicenter(Transition transition) {
                    return viewRect;
                }
            });
        }

        TransitionSet sharedElementTransition = new TransitionSet()
                .addTransition(new ChangeBounds())
                .addTransition(new ChangeTransform())
                .addTransition(new ChangeImageTransform())
                .setDuration(TRANSITION_DURATION).setInterpolator(transitionInterpolator);

        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setEnterTransition(sharedElementTransition);
        detailsFragment.setExitTransition(sharedElementTransition);


        getFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.container, detailsFragment)
                .addToBackStack(null).addSharedElement(sharedView, getString(R.string.transition_name) ).commit();

}





    }
