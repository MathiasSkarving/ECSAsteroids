package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.ecs.Component;

public class AsteroidComponent extends Component {
    public int splitsInto;
    public int splitsLeft;
    public double scale;

    public AsteroidComponent(int splitsLeft, int splitsInto, double scale) {
        this.splitsLeft = splitsLeft;
        this.splitsInto = splitsInto;
        this.scale = scale;
    }
}
