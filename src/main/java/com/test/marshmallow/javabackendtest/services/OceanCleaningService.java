package com.test.marshmallow.javabackendtest.services;

import com.test.marshmallow.javabackendtest.domain.Coordinates;
import com.test.marshmallow.javabackendtest.domain.OceanCleaner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OceanCleaningService {
    public JSONObject process(JSONObject jsonRequest) {

        Coordinates areaSize = getCoordinatesForAreaSize(jsonRequest);
        Coordinates oceanCleanerPosition = getCoordinatesForOceanCleanerPosition(jsonRequest);
        List<Coordinates> oilPatchesPositions = getCoordinatesForOilPatches(jsonRequest);

        int amountOfPatchesBeforeCleaning = oilPatchesPositions.size();

        OceanCleaner oceanCleaner = new OceanCleaner(areaSize, oceanCleanerPosition, oilPatchesPositions);
        oceanCleaner.navigateAndClean(getNavigationInstructions(jsonRequest));

        JSONObject response = prepareResponse(oceanCleaner.getCleanerPosition(),
				amountOfPatchesBeforeCleaning - oceanCleaner.numberOfPatchesInArea());
        return response;
    }

    private String getNavigationInstructions(JSONObject jsonRequest) {
        return jsonRequest.getString("navigationInstructions");
    }

    private List<Coordinates> getCoordinatesForOilPatches(JSONObject jsonRequest) {
        JSONArray oilPatchesArray = (JSONArray) jsonRequest.get("oilPatches");
        List<Coordinates> oilPatches = new ArrayList<>();
        for (Object currentObject : oilPatchesArray) {
            if (currentObject instanceof JSONArray) {
                oilPatches.add(new Coordinates(((JSONArray) currentObject).getInt(0),
                        ((JSONArray) currentObject).getInt(1)));
            }
        }
        return oilPatches;
    }

    private Coordinates getCoordinatesForOceanCleanerPosition(JSONObject jsonRequest) {
        JSONArray cleanerPositionArray = (JSONArray) jsonRequest.get("startingPosition");
        return new Coordinates(cleanerPositionArray.getInt(0), cleanerPositionArray.getInt(1));
    }

    private Coordinates getCoordinatesForAreaSize(JSONObject jsonRequest) {
        JSONArray areaSizeArray = (JSONArray) jsonRequest.get("areaSize");
        return new Coordinates((areaSizeArray.getInt(0)), areaSizeArray.getInt(1));
    }

    private JSONObject prepareResponse(Coordinates lastPosition, int oilPatchesCleaned) {
		JSONObject response = new JSONObject();
		JSONArray finalPosition = new JSONArray();
		finalPosition.put(lastPosition.getX());
		finalPosition.put(lastPosition.getY());
        response.put("finalPosition", finalPosition);
		response.put("oilPatchesCleaned", oilPatchesCleaned);
		return response;
	}
}
