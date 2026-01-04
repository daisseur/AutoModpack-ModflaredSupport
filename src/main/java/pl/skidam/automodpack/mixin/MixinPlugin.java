package pl.skidam.automodpack.mixin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {
    private boolean isFabric = false;
    
    @Override
    public void onLoad(String mixinPackage) {
        // Needed for versions < 1.18
//        MixinExtrasBootstrap.init();
        
        // Detect if we're running on Fabric by checking for Fabric Loader class
        try {
            Class.forName("net.fabricmc.loader.api.FabricLoader");
            isFabric = true;
        } catch (ClassNotFoundException e) {
            isFabric = false;
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // Skip Fabric-specific mixins when not running on Fabric
        if (mixinClassName.contains("FabricLoginMixin") && !isFabric) {
            return false;
        }
        
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
