import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.score.ScorePlugin;
import dk.sdu.cbse.score.ScoreSystem;

module Score.mod {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires spring.core;
    requires spring.context;
    requires spring.beans;
    requires spring.web;
    requires micrometer.observation;
    requires micrometer.commons;
    requires com.fasterxml.jackson.databind;

    opens dk.sdu.cbse.score to spring.core, spring.beans, spring.context;

    provides IGamePlugin with ScorePlugin, ScoreSystem;
}