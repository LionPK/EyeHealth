package com.crud.singl.eyehealth.loader;
import com.crud.singl.eyehealth.model.Period;
import com.crud.singl.eyehealth.model.StatEntry;

import java.util.List;


/**
 * Created by singl on 3/19/2018.
 */

public interface StatProvider {

    List<StatEntry> loadStats(Period period);

    List<StatEntry> loadStatsWithLimit(Period period, int maxCount);
}
