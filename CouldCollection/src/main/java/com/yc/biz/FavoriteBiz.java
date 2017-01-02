package com.yc.biz;

import java.util.List;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;

public interface FavoriteBiz {
	public int addFavorite(Favorite fav,Tag tag);
	public List<Favorite> findFavoriteByType(Favorite fav);

}
