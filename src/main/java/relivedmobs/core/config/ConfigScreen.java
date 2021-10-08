package relivedmobs.core.config;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.client.settings.SliderPercentageOption;

public final class ConfigScreen extends Screen {
	private final Screen parentScreen;
	private OptionsRowList optionsRowList;
	
	private static final int OPTIONS_LIST_TOP_HEIGHT = 24;
    /** Distance from bottom of the screen to the options row list's bottom */
    private static final int OPTIONS_LIST_BOTTOM_OFFSET = 32;
    /** Height of each item in the options row list */
    private static final int OPTIONS_LIST_ITEM_HEIGHT = 25;

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int DONE_BUTTON_TOP_OFFSET = 26;
    
    /** Distance from top of the screen to this GUI's title */
    private static final int TITLE_HEIGHT = 8;

    public ConfigScreen(Screen parentScreen) {
        super(new TranslationTextComponent("relivedmobs.configGui.title",
                "Relived Mobs Settings"));
        this.parentScreen = parentScreen;
    }
    
    @Override
    protected void init() {
        this.optionsRowList = new OptionsRowList(
                this.minecraft, this.width, this.height,
                OPTIONS_LIST_TOP_HEIGHT,
                this.height - OPTIONS_LIST_BOTTOM_OFFSET,
                OPTIONS_LIST_ITEM_HEIGHT
        );
        this.addBooleanOption("anaconda_spawn", RelivedMobsConfig.spawn_anaconda);
        this.addBooleanOption("hyena_spawn", RelivedMobsConfig.spawn_hyena);
        this.addBooleanOption("bison_spawn", RelivedMobsConfig.spawn_bison);
        this.addBooleanOption("fallowdeer_spawn", RelivedMobsConfig.spawn_fallowdeer);
        this.addBooleanOption("puffin_spawn", RelivedMobsConfig.spawn_puffin);
        this.addBooleanOption("seahorse_spawn", RelivedMobsConfig.spawn_seahorse);
        												
        this.addSlideOption("anaconda_health", RelivedMobsConfig.anaconda_attack_damage);
        this.addSlideOption("anaconda_speed", RelivedMobsConfig.anaconda_attack_damage);
        this.addSlideOption("anaconda_attack_damage", RelivedMobsConfig.anaconda_attack_damage);

        this.addSlideOption("hyena_health", RelivedMobsConfig.hyena_attack_damage);
        this.addSlideOption("hyena_speed", RelivedMobsConfig.hyena_attack_damage);
        this.addSlideOption("hyena_attack_damage", RelivedMobsConfig.hyena_attack_damage);
        
        this.addSlideOption("bison_health", RelivedMobsConfig.bison_attack_damage);
        this.addSlideOption("bison_speed", RelivedMobsConfig.bison_attack_damage);
        this.addSlideOption("bison_attack_damage", RelivedMobsConfig.bison_attack_damage);
        
        this.addSlideOption("fallow_deer_health", RelivedMobsConfig.fallow_deer_attack_damage);
        this.addSlideOption("fallow_deer_speed", RelivedMobsConfig.fallow_deer_attack_damage);
        this.addSlideOption("fallow_deer_attack_damage", RelivedMobsConfig.fallow_deer_attack_damage);
        
        this.addSlideOption("puffin_health", RelivedMobsConfig.puffin_attack_damage);
        this.addSlideOption("puffin_speed", RelivedMobsConfig.puffin_attack_damage);
        this.addSlideOption("puffin_attack_damage", RelivedMobsConfig.puffin_attack_damage);
        
        this.addSlideOption("sea_horse_health", RelivedMobsConfig.sea_horse_attack_damage);
        this.addSlideOption("sea_horse_speed", RelivedMobsConfig.sea_horse_attack_damage);
        this.addSlideOption("sea_horse_attack_damage", RelivedMobsConfig.sea_horse_attack_damage);
        
        this.addIntSlideOption("anaconda_min_spawn_value", RelivedMobsConfig.anaconda_min_spawn_value);
        this.addIntSlideOption("anaconda_min_spawn_value", RelivedMobsConfig.anaconda_max_spawn_value);
        
        this.addIntSlideOption("bison_min_spawn_value", RelivedMobsConfig.bison_min_spawn_value);
        this.addIntSlideOption("bison_min_spawn_value", RelivedMobsConfig.bison_max_spawn_value);
        
        this.children.add(this.optionsRowList);

        // "Done" button
        this.addButton(new Button(
                (this.width - BUTTON_WIDTH) / 2,
                this.height - DONE_BUTTON_TOP_OFFSET,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                new TranslationTextComponent("gui.done"),
                button -> this.onClose()
        ));
    }
    
    @Override
    public void onClose() {
        this.minecraft.setScreen(parentScreen);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.optionsRowList.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, this.font, this.title.getString(),
                this.width / 2, TITLE_HEIGHT, 0xFFFFFF);
        
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
    
    void addBooleanOption(String name, ForgeConfigSpec.BooleanValue value) {
    	this.optionsRowList.addBig(new BooleanOption(
                "relivedmobs.configGui." + name + ".title",
                unused -> value.get(),
                (unused, newValue) -> value.set(newValue)
        ));
    }
    
    void addSlideOption(String name, ForgeConfigSpec.ConfigValue<Double> value) {
    	this.optionsRowList.addBig(new SliderPercentageOption(
                "relivedmobs.configGui." + name + ".title",
                0.0, this.width, 1.0F,
                unused -> value.get(),
                (unused, newValue) -> value.set(newValue.doubleValue()),
                (gs, option) -> new StringTextComponent(I18n.get(
                        "relivedmobs.configGui." + name + ".title"
                ) + ": " + (int) option.get(gs))));
    }
    void addIntSlideOption(String name, ForgeConfigSpec.ConfigValue<Integer> value) {
    	this.optionsRowList.addBig(new SliderPercentageOption(
                "relivedmobs.configGui." + name + ".title",
                0.0, this.width, 1.0F,
                unused -> (double)value.get(),
                (unused, newValue) -> value.set(newValue.intValue()),
                (gs, option) -> new StringTextComponent(I18n.get(
                        "relivedmobs.configGui." + name + ".title"
                ) + ": " + (int) option.get(gs))));
    }
}