package ilk;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.ui.SectorMapAPI;
import ilk.world.utils.CommissionEffects;
import org.apache.log4j.Level;
import org.json.JSONObject;

public class MarketInsightModPlugin extends BaseModPlugin {

  public static final String ID = "mayorate";
  private static final String SETTINGS_FILE = "mayorate_settings.json";
  private static boolean isExerelin;

  public static boolean getIsExerelin() {
    return isExerelin;
  }

  private static void initMarketSkill() {

  }


  @Override
  public void onApplicationLoad() throws Exception {
    JSONObject settings = Global.getSettings().loadJSON(SETTINGS_FILE);
    setLogLevel(Level.toLevel(settings.optString("logLevel", "ERROR"), Level.ERROR));

    SectorMapAPI s;
  }

  @Override
  public void onNewGame() {
    initMarketSkill();
  }

  private static void setLogLevel(Level level) {
    Global.getLogger(CommissionEffects.class).setLevel(level);
  }
}
