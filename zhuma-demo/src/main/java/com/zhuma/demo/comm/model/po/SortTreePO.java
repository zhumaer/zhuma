package com.zhuma.demo.comm.model.po;

public interface SortTreePO<PK> extends TreePO<PK>, Comparable<SortTreePO> {

	Long getSort();

	void setSort(Long sort);

	@Override
	default int compareTo(SortTreePO sortTree) {
		if (sortTree == null) {
			return -1;
		}

		return Long.compare(getSort() == null ? 0 : getSort(), sortTree.getSort() == null ? 0 : sortTree.getSort());
	}
	
}
