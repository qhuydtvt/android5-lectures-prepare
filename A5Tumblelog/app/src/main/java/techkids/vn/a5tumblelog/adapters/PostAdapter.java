package techkids.vn.a5tumblelog.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techkids.vn.a5tumblelog.R;
import techkids.vn.a5tumblelog.models.Post;
import techkids.vn.a5tumblelog.viewholders.PostViewHolder;

/**
 * Created by apple on 10/23/16.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    // Create new
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 1: Inflate View
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_post, parent, false);

        // 2: Create View holder
        PostViewHolder postViewHolder = new PostViewHolder(itemView);

        return postViewHolder;
    }


    // Update
    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        // Model
        Post post = Post.list.get(position);

        // Bind
        holder.bind(post);
    }

    // Get count
    @Override
    public int getItemCount() {
        return Post.list.size();
    }
}
