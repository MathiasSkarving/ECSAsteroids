package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.ecs.Component;

public class AsteroidComponent extends Component {
    public int splitsLeft;
    public double scale;

    public AsteroidComponent(int splitsLeft, double scale) {
        this.splitsLeft = splitsLeft;
        this.scale = scale;
    }
}
