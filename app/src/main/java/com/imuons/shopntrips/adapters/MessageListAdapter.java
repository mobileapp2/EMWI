package com.imuons.shopntrips.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imuons.shopntrips.R;
import com.imuons.shopntrips.model.ChatMsg;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private static final int nothing = 3;
    String loadedImage;

    Context mContext;
    private List<ChatMsg> mMessageList;

    RecyclerViewClickListener listener;
    public MessageListAdapter(Context context, List<ChatMsg> messageList, RecyclerViewClickListener listener) {
        mContext = context;
        mMessageList = messageList;
//        mMessageList.addAll(messageList);
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        ChatMsg message = mMessageList.get(position);


       // String userId = SharedPreferenceUtils.getId(mContext).toUpperCase();

        if (message.getRight() != null ) {
            //            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else if (message.getLeft() != null ) {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;


        } else
            return nothing;
    }


    // Inflates the appropriate layout according to the ViewType.
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ChatMsg message = mMessageList.get(position);
        holder.setIsRecyclable(false);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:


                ((SentMessageHolder) holder).bind(message);

                ((SentMessageHolder) holder).sentAttachment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        listener.onClickCallback(v, position, message.getRight().getAttachment());
                    }
                });


                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);

                ((ReceivedMessageHolder) holder).attachmentAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClickCallback(v, position, message.getLeft().getAttachment().toString());

                    }
                });


                break;
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;
        ImageView sentAttachment;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            sentAttachment = itemView.findViewById(R.id.upload_image);
            sentAttachment.setVisibility(View.GONE);


        }

        void bind(ChatMsg message) {

            if (message.getRight().getComments() != null) {
                messageText.setVisibility(View.VISIBLE);
                timeText.setVisibility(View.VISIBLE);
                messageText.setText((String) message.getRight().getComments());
            } else {
                sentAttachment.setVisibility(View.GONE);
            }
            timeText.setText(message.getRight().getEntryTime());

            if (message.getRight().getAttachment() != null) {
                sentAttachment.setVisibility(View.VISIBLE);
                loadedImage = message.getRight().getAttachment();
                Glide.with(mContext).load(loadedImage).into(sentAttachment);
            } else {
                sentAttachment.setVisibility(View.GONE);
            }
            if (message.getRight().getAttachment().equals("") && message.getRight().getAttachment() == null ){
                sentAttachment.setVisibility(View.GONE);
            }
        }


    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ImageView attachmentAdmin;

        ReceivedMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
//            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            attachmentAdmin = (ImageView) itemView.findViewById(R.id.upload_image_admin);
            attachmentAdmin.setVisibility(View.GONE);
        }

        void bind(ChatMsg message) {

            if (message.getLeft().getComments() != null) {

                messageText.setVisibility(View.VISIBLE);
                timeText.setVisibility(View.VISIBLE);
                messageText.setText((String) message.getLeft().getComments());
            } else {
                attachmentAdmin.setVisibility(View.GONE);

            }

            timeText.setText(message.getLeft().getEntryTime());

            if (message.getLeft().getAttachment() != null) {
                attachmentAdmin.setVisibility(View.VISIBLE);
                String loadedImage = message.getLeft().getAttachment().toString();
                Glide.with(mContext).load(loadedImage).into(attachmentAdmin);
            } else {
                attachmentAdmin.setVisibility(View.GONE);
            }
            if (message.getLeft().getAttachment().equals("") && message.getLeft().getAttachment() == null ){
                attachmentAdmin.setVisibility(View.GONE);
            }
        }
    }


    public interface RecyclerViewClickListener {

        void onClickCallback(View view, int position, String url);

    }
}
