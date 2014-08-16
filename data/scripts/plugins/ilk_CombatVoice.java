package data.scripts.plugins;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.EveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipEngineControllerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import java.util.List;
import java.util.Iterator;

public class ilk_CombatVoice implements EveryFrameCombatPlugin {

    //define combat voice sounds and booleans for whether it's already warned you or not
    private static final String DAMAGE_WARN = "damage_warning";
    private static boolean damage_warn_bool = false;

    private static final String DAMAGE_CRITICAL = "damage_critical";
    private static boolean damage_critical_bool = false;

    private static final String ENGINES_OFFLINE = "engines_offline";
    private static boolean engine_failure_bool = false;
    private static boolean engine_warn_bool = false;
    
    //don't touch
    private CombatEngineAPI engine;
    private final IntervalUtil VoiceFrameTracker = new IntervalUtil(0.5f, 1f);

    @Override
    public void advance(float amount, List events) {
        
        ShipAPI player;

        VoiceFrameTracker.advance(amount);
        

        if (VoiceFrameTracker.intervalElapsed()) {
            //player related voice tracking in this section
            player = engine.getPlayerShip();
            
            //play DAMAGE_CRITICAL sound if player health drops to 10% and it hasn't already told you
            if ((player.getHitpoints() / player.getMaxHitpoints() < 0.1) && (damage_critical_bool == false)) {
                Global.getSoundPlayer().playSound(DAMAGE_CRITICAL, 1f, 1f, player.getLocation(), player.getVelocity());
                damage_warn_bool = true;
                damage_critical_bool = true;
            }

            //play DAMAGE_WARN sound if player health drops to 50%
            if ((player.getHitpoints() / player.getMaxHitpoints() < 0.4) && (damage_warn_bool == false)) {
                Global.getSoundPlayer().playSound(DAMAGE_WARN, 1f, 1f, player.getLocation(), player.getVelocity());
                damage_warn_bool = true;
            }

            //play ENGINES_DISABLED sound if all player engines are disabled
            for (Iterator engines = player.getEngineController().getShipEngines().iterator(); engines.hasNext(); ) {  
            ShipEngineControllerAPI.ShipEngineAPI an_engine = (ShipEngineControllerAPI.ShipEngineAPI) engines.next();  
                if (an_engine.isDisabled() == false) {
                    //break if an engine is active
                    engine_failure_bool = false;
                    engine_warn_bool = false;
                    break;
                } else {
                    //ok, so everything's disabled... register engines as disabled
                    engine_failure_bool = true;
                }
            }
            if (engine_failure_bool && !engine_warn_bool) {
                //play sound if it hasn't warned you already and engines are offline
                Global.getSoundPlayer().playSound(ENGINES_OFFLINE, 1f, 1f, player.getLocation(), player.getVelocity());
                engine_warn_bool = true;
            }
        }
    }

    @Override
    public void init(CombatEngineAPI engine) {
        this.engine = engine;
    }
}
