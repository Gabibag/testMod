package gabibag.modid;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LaunchMechanism extends Item {

    public LaunchMechanism(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        World world = context.getWorld();
        for (int i = 0; i < 6; i++) {
            int x = context.getBlockPos().getX();
            int y = context.getBlockPos().getY();
            int z = context.getBlockPos().getZ();
            for (int j = -1; j <= 1; j+=2) {
                TntEntity tntEntity = new TntEntity(world, x + (j * i), y + 1, z + (j * i), playerEntity);
                tntEntity.setFuse(20 + (i * 3));
                tntEntity.setVelocity(0, 0.64 * (i*1.1),0);
                world.spawnEntity(tntEntity);
                playerEntity.playSound(SoundEvents.ENTITY_TNT_PRIMED, 1.0F, 1.0F);
            }
            for (int j = -1; j <= 1; j+=2) {
                TntEntity tntEntity = new TntEntity(world, x - (j * i), y + 1, z + (j * i), playerEntity);
                tntEntity.setFuse(20 + (i * 3));
                world.spawnEntity(tntEntity);
                tntEntity.setVelocity(0, 0.64 * (i*1.1),0);
                playerEntity.playSound(SoundEvents.ENTITY_TNT_PRIMED, 1.0F, 1.0F);
            }

        }
        return super.useOnBlock(context);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("Right click to fly,").formatted(Formatting.RESET));

    }


}
