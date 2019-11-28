const Plane = require("./Plane");

class ExperimentalPlane extends Plane {
  constructor(
    model,
    maxSpeed,
    maxFlightDistance,
    maxLoadCapacity,
    type,
    ClassificationLevelTypes
  ) {
    super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
    this._type = type;
    this._classificationLevel = ClassificationLevelTypes;
  }

  get type() {
    return this._type;
  }

  set type(value) {
    this._type = value;
  }

  get ClassificationLevelTypes() {
    return this._classificationLevel;
  }

  set ClassificationLevelTypes(value) {
    this._classificationLevel = value;
  }
}

module.exports = ExperimentalPlane;
