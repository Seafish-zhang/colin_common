package utils.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class SortUtil {

	// 排序
	public static List<Map> sortByOrder(List<Map> data, final String sortOrder, final int sortType) {
		// TODO Auto-generated method stub
		Collections.sort(data, new Comparator<Map>() {
			public int compare(Map o1, Map o2) {
				Long a = Long.valueOf(o1.get(sortOrder).toString());
				Long b = Long.valueOf(o2.get(sortOrder).toString());
				// 升序
				if (sortType == 1)
					return a.compareTo(b);
				// 降序
				else
					return b.compareTo(a);
			}
		});
		return data;
	}

}
