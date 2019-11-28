const PassengerPlane = require("./Planes/PassengerPlane");
const MilitaryPlane = require("./Planes/MilitaryPlane");
const MilitaryType = require("./models/militaryType");
const ExperimentalPlane = require("./Planes/ExperimentalPlane");

class Airport {
  constructor(planes) {
    this.planes = planes;
  }

  getPlanes() {
    return this.planes;
  }

  getPassengerPlanes() {
    return this.planes.filter(plane => plane instanceof PassengerPlane);
  }

  getMilitaryPlanes() {
    return this.planes.filter(plane => plane instanceof MilitaryPlane);
  }

  getTransportMilitaryPlanes() {
    return this.getMilitaryPlanes().filter(
      militaryPlane =>
        militaryPlane.militaryType === MilitaryType.TYPE_TRANSPORT
    );
  }

  getBomberMilitaryPlanes() {
    return this.getMilitaryPlanes().filter(
      militaryPlane => militaryPlane.militaryType === MilitaryType.BOMBER
    );
  }

  getExperimentalPlanes() {
    return this.planes.filter(plane => plane instanceof ExperimentalPlane);
  }

  getPassengerPlaneWithMaxPassengersCapacity() {
    let passengerPlanes = this.getPassengerPlanes();
    let planeWithMaxCapacity = passengerPlanes[0];
    for (let i = 0; i < passengerPlanes.length; i++) {
      if (
        passengerPlanes[i].passengersCapacity >
        planeWithMaxCapacity.passengersCapacity
      ) {
        planeWithMaxCapacity = passengerPlanes[i];
      }
    }
    return planeWithMaxCapacity;
  }

  sortByMaxDistance() {
    this.planes.sort((a, b) =>
      a.maxFlightDistance > b.maxFlightDistance ? 1 : -1
    );
    return this;
  }

  sortByMaxSpeed() {
    this.planes.sort((a, b) => (a.maxSpeed > b.maxSpeed ? 1 : -1));
    return this;
  }

  sortByMaxLoadCapacity() {
    this.planes.sort((a, b) =>
      a.maxLoadCapacity > b.maxLoadCapacity ? 1 : -1
    );
    return this;
  }

  static print(planes) {
    return JSON.stringify(planes);
  }
}

module.exports = Airport;
