package coursework.model;

public enum ModelCondition {
    MOVING_RIGHT {
        @Override
        public short getValue() {
            return 1;
        }

        @Override
        public void performAction(Model model) {
            model.transit(5, 0, 0);
        }
    },
    MOVING_LEFT {
        @Override
        public short getValue() {
            return 2;
        }

        @Override
        public void performAction(Model model) {
            model.transit(-5, 0, 0);
        }
    },
    MOVING_UP {
        @Override
        public short getValue() {
            return 4;
        }

        @Override
        public void performAction(Model model) {
            model.transit(0, 5, 0);
        }
    },
    MOVING_DOWN {
        @Override
        public short getValue() {
            return 8;
        }

        @Override
        public void performAction(Model model) {
            model.transit(0, -5, 0);
        }
    },
    ROTATING_X_POS {
        @Override
        public short getValue() {
            return 16;
        }

        @Override
        public void performAction(Model model) {
            model.rotate(1, 0, 0);
        }
    },
    ROTATING_X_NEG {
        @Override
        public short getValue() {
            return 32;
        }

        @Override
        public void performAction(Model model) {
            model.rotate(-1, 0, 0);
        }
    },
    ROTATING_Y_POS {
        @Override
        public short getValue() {
            return 64;
        }

        @Override
        public void performAction(Model model) {
            model.rotate(0, 1, 0);
        }
    },
    ROTATING_Y_NEG {
        @Override
        public short getValue() {
            return 128;
        }

        @Override
        public void performAction(Model model) {
            model.rotate(0, -1, 0);
        }
    },
    ROTATING_Z_POS {
        @Override
        public short getValue() {
            return 256;
        }

        @Override
        public void performAction(Model model) {
            model.rotate(0, 0, 1);
        }
    },
    ROTATING_Z_NEG {
        @Override
        public short getValue() {
            return 512;
        }

        @Override
        public void performAction(Model model) {
            model.rotate(0, 0, -1);
        }
    },
    MOVING_Z_POS {
        @Override
        public short getValue() {
            return 1024;
        }

        @Override
        public void performAction(Model model) {
            model.transit(0, 0, 5);
        }
    },
    MOVING_Z_NEG {
        @Override
        public short getValue() {
            return 2048;
        }

        @Override
        public void performAction(Model model) {
            model.transit(0, 0, -5);
        }
    };

    public abstract short getValue();

    public abstract void performAction(Model model);
}
