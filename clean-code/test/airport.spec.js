const assert = require("chai").assert;

const MilitaryPlane = require("../classes/MilitaryPlane");
const PassengerPlane = require("../classes/PassengerPlane");
const ExperimentalPlane = require("../classes/ExperimentalPlane");
const Airport = require("../classes/Airport");
const MilitaryTypes = require("../types/military");
const ExperimentalTypes = require("../types/experimental");
const ClassificationLevelTypes = require("../types/classificationLevel");

describe("My Test", () => {
  const planes = [
    new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
    new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
    new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
    new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
    new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
    new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
    new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
    new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
    new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryTypes.Bomber),
    new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryTypes.Bomber),
    new MilitaryPlane(
      "B-52 Stratofortress",
      1000,
      20000,
      80000,
      MilitaryTypes.Bomber
    ),
    new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryTypes.Fighter),
    new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryTypes.Fighter),
    new MilitaryPlane(
      "C-130 Hercules",
      650,
      5000,
      110000,
      MilitaryTypes.Transport
    ),
    new ExperimentalPlane(
      "Bell X-14",
      277,
      482,
      500,
      ExperimentalTypes.HighAltitude,
      ClassificationLevelTypes.Secret
    ),
    new ExperimentalPlane(
      "Ryan X-13 Vertijet",
      560,
      307,
      500,
      ExperimentalTypes.VTOL,
      ClassificationLevelTypes.TopSecret
    )
  ];
  let planeWithMaxPassengerCapacity = new PassengerPlane(
    "Boeing-747",
    980,
    16100,
    70500,
    242
  );

  it("should have military Planes with transport type", () => {
    const airport = new Airport(planes);
    const transportMilitaryPlanes = airport.getTransportMilitaryPlanes();
    for (let militaryPlane of transportMilitaryPlanes)
      assert.equal(militaryPlane.militaryType, MilitaryTypes.Transport);
  });

  it("should check passenger plane with max capacity", () => {
    const airport = new Airport(planes);
    const expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
    console.log({
      expectedPlaneWithMaxPassengersCapacity,
      planeWithMaxPassengerCapacity
    });
    assert.deepEqual(
      expectedPlaneWithMaxPassengersCapacity,
      planeWithMaxPassengerCapacity
    );
  });

  it("sortByMaxLoadCapacity should sort by max load capacity", () => {
    const airport = new Airport(planes);
    const planesSortedByMaxLoadCapacity = airport
      .sortByMaxLoadCapacity()
      .getPlanes();
    for (let i = 0; i < planesSortedByMaxLoadCapacity.length - 1; i++) {
      const currentPlane = planesSortedByMaxLoadCapacity[i];
      const nextPlane = planesSortedByMaxLoadCapacity[i + 1];
      assert.isTrue(currentPlane.maxLoadCapacity <= nextPlane.maxLoadCapacity);
    }
  });

  it("getBomberMilitaryPlanes should return array of bombers", () => {
    const airport = new Airport(planes);
    const bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
    for (let militaryPlane of bomberMilitaryPlanes)
      assert.equal(militaryPlane.militaryType, MilitaryTypes.Bomber);
  });

  it("should check that experimental planes has classification level higher than unclassified", () => {
    const airport = new Airport(planes);
    const bomberMilitaryPlanes = airport.getExperimentalPlanes();
    for (let bomber of bomberMilitaryPlanes)
      assert.notEqual(
        bomber.classificationLevel,
        ClassificationLevelTypes.Unclassified
      );
  });
});
