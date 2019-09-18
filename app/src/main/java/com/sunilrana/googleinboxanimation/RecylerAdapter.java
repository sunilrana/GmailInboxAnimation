package com.sunilrana.googleinboxanimation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder>{
    private ArrayList<String> listdata = new ArrayList<>();
    OnItemClickListener mListener;

    // RecyclerView recyclerView;
    public RecylerAdapter(OnItemClickListener listener) {
      //  this.listdata = listdata;
        mListener = listener;
    }

    public void setData (ArrayList<String> data){
        listdata = data;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mListener, position, listdata.get(position));
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView;

        }

        public void bind(final OnItemClickListener listener, final int position, String data){
            textView.setText(data);
            itemView.setTransitionName(data);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(itemView , position);
                }
            });
        }
    }
}


//private inner class EmailAdapter : RecyclerView.Adapter<EmailViewHolder>() {
//private var emails: List<String> = emptyList()
//
//        fun setData(emails: List<String>) {
//        this.emails = emails
//        notifyDataSetChanged()
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder =
//        EmailViewHolder(LayoutInflater.from(parent.context).inflate(layout.list_item, parent, false))
//
//        override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
//        fun onViewClick() {
//        tapPosition = position
//        holder.itemView.getGlobalVisibleRect(viewRect)
//
//        (this@EmailListFragment.exitTransition as Transition).epicenterCallback =
//        object : Transition.EpicenterCallback() {
//        override fun onGetEpicenter(transition: Transition) = viewRect
//        }
//
//        val sharedElementTransition = TransitionSet()
//        .addTransition(ChangeBounds())
//        .addTransition(ChangeTransform())
//        .addTransition(ChangeImageTransform()).apply {
//        duration = TRANSITION_DURATION
//        setCommonInterpolator(transitionInterpolator)
//        }
//
//        val fragment = DetailsFragment().apply {
//        sharedElementEnterTransition = sharedElementTransition
//        sharedElementReturnTransition = sharedElementTransition
//        }
//
//        activity!!.supportFragmentManager
//        .beginTransaction()
//        .setReorderingAllowed(true)
//        .replace(R.id.container, fragment)
//        .addToBackStack(null)
//        .addSharedElement(holder.itemView, getString(R.string.transition_name))
//        .commit()
//        }
//
//        holder.bindData(emails[position], ::onViewClick)
//        }
//
//        override fun getItemCount() = emails.size
//        }
//        }
//
//private class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//private var email: String? = null
//
//        fun bindData(email: String, expandHandler: () -> Unit) {
//        this.email = email
//        itemView.setOnClickListener { expandHandler() }
//        itemView.transitionName = email
//        (itemView as TextView).text = email
//        }
//        }
