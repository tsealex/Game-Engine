package game;

import engine.GameClock;
import engine.GameEngine;
import graphics.GFXDataCell;
import graphics.GFXService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EffectManager implements EffectService {
	
	private GFXService gfxService;
	
	private int nextID = 0;
	Map<Integer, GFXDataCell> effectMap;
	List<EffectRequest> rqList1, rqList2, rqList3;
	
	public EffectManager(GameEngine engine)	{
		gfxService = engine.Graphics();
		effectMap = new HashMap<Integer, GFXDataCell>();
		rqList1 = new ArrayList<EffectRequest>();
		rqList2 = new ArrayList<EffectRequest>();
		rqList3 = new ArrayList<EffectRequest>();
	}
	
	public int registerEffect(int width, int height, int regNum, String path)	{
		GFXDataCell gfxCell;
		if(regNum > 1)
			gfxCell = new GFXDataCell(width, height, regNum, path);
		else
			gfxCell = new GFXDataCell(width, height, path);
		gfxService.register(gfxCell, "Effect" + nextID);
		effectMap.put(nextID, gfxCell);
		return nextID;
	}
	
	public void requestForEffect(EffectRequest request, int listNumber)	{
		// x and y are in the game space
		if(listNumber == 1)
			rqList1.add(request);
		if(listNumber == 2)
			rqList2.add(request);
		else if(listNumber == 3)
			rqList3.add(request);
	}
	
	public void drawEffect(int listNumber)	{
		List<EffectRequest> rqList;
		
		if(listNumber == 1)
			rqList = rqList1;
		else if(listNumber == 2)
			rqList = rqList2;
		else if(listNumber == 3)
			rqList = rqList3;
		else 
			return;

		GFXDataCell gfxCell;
		for(int x = 0; x < rqList.size(); x++)	{
			EffectRequest rq = rqList.get(x);
			if(rq.intData[1] > GameClock.Time())	{
				gfxCell = effectMap.get(rq.intData[0]);
				gfxCell.setAnimation(rq.intData[2]);
				gfxCell.setAttribute(rq.floatData[0], rq.floatData[1], rq.floatData[2], rq.floatData[3]);
				gfxService.requestForRender(gfxCell);
			} else	{
				rqList.remove(x);
				rq = null;
				x--;
			}
		}
		gfxCell = null;
	}
	
}
