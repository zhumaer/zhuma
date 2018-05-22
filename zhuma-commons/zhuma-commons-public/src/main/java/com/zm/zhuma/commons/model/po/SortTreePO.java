package com.zm.zhuma.commons.model.po;

public interface SortTreePO<PK> extends TreePO<PK>, Comparable<SortTreePO> {

	Integer getSort();

	void setSort(Integer sort);

	@Override
	default int compareTo(SortTreePO sortTree) {
		if (sortTree == null) {
			return -1;
		}

		return Integer.compare(getSort() == null ? 0 : getSort(), sortTree.getSort() == null ? 0 : sortTree.getSort());
	}
	
}
