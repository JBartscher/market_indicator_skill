import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketDemandAPI;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;

public class MarketInfo {
    public void foo() {
        for (MarketAPI market : Global.getSector().getEconomy().getMarketsCopy()) {
            Global.getLogger(this.getClass()).info(market.getDemand(Commodities.FUEL));
        }
    }
}
