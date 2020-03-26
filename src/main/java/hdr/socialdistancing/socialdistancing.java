package hdr.socialdistancing;
import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

@Plugin(id = "socialdistancing", name = "Social Distancing")
public class socialdistancing {
    @Listener
    public void onPlayerMove(MoveEntityEvent e, @Root Player player){
        Location<World> loc1 = player.getLocation();
        for(Player p: Sponge.getServer().getOnlinePlayers()){
            Location<World> loc2 = p.getLocation();
            double playerDistance = loc1.getPosition().distance(loc2.getPosition());
            if(playerDistance < 3 && playerDistance != 0){
                double xVelocity;
                double zVelocity;
                if(player.getVelocity().getX() < 0){ xVelocity = player.getVelocity().getX() + 2; } else { xVelocity = player.getVelocity().getX() - 2;}
                if(player.getVelocity().getZ() < 0){ zVelocity = player.getVelocity().getZ() + 2; } else { zVelocity = player.getVelocity().getZ() - 2;}
                player.setVelocity(new Vector3d(xVelocity, player.getVelocity().getY(), zVelocity));
                player.sendMessage(Text.of(TextColors.RED, TextStyles.BOLD, "Please practice Social Distancing and stay at least 2 blocks away from other players!"));
            }
        }
    }
}
