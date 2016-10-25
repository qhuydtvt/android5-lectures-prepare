package techkids.vn.a5tumblelog.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.vn.a5tumblelog.R;
import techkids.vn.a5tumblelog.models.Post;

/**
 * Created by apple on 10/23/16.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_title)
    public TextView tvTitle;

    @BindView(R.id.tv_content)
    public TextView tvContent;

    public PostViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Post post) {
        tvTitle.setText(post.getTitle());
        tvContent.setText(post.getContent());
    }
}
