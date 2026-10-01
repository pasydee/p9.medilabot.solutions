// Script d'initialisation MongoDB (exécuté par mongosh au premier démarrage)
// Doit être monté sur /docker-entrypoint-initdb.d/notes.js (extension .js obligatoire,
// .json n'est PAS exécuté par l'image officielle mongo)

db = db.getSiblingDB('notes');

db.notes.insertMany([
  {
    _id: ObjectId("6a885ffafbf51082518f0318"),
    patientId: 1,
    date: ISODate("2026-08-21T14:00:00.000Z"),
    content: "Le patient déclare qu'il 'se sent très bien'.\nPoids égal ou inférieur au poids recommandé.",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a886004fbf51082518f031a"),
    patientId: 2,
    date: ISODate("2026-08-21T14:00:00.000Z"),
    content: "Le patient déclare qu'il ressent beaucoup de stress au travail.\nIl se plaint également que son audition est anormale dernièrement.",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a886015fbf51082518f031c"),
    patientId: 2,
    date: ISODate("2026-08-21T14:00:00.000Z"),
    content: "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois.\nIl remarque également que son audition continue d'être anormale.",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a886020fbf51082518f031e"),
    patientId: 3,
    date: ISODate("2026-08-21T14:00:00.000Z"),
    content: "Le patient déclare qu'il fume depuis peu.",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a886028fbf51082518f0320"),
    patientId: 3,
    date: ISODate("2026-08-21T14:00:00.000Z"),
    content: "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière.\nIl se plaint également de crises d'apnée respiratoire anormales.\nTests de laboratoire indiquant un taux de cholestérol LDL élevé.",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a886033fbf51082518f0322"),
    patientId: 4,
    date: ISODate("2026-08-21T14:00:00.000Z"),
    content: "Le patient déclare qu'il lui est devenu difficile de monter les escaliers.\nIl se plaint également d'être essoufflé.\nTests de laboratoire indiquant que les anticorps sont élevés.\nRéaction aux médicaments.",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a88603cfbf51082518f0324"),
    patientId: 4,
    date: ISODate("2026-08-21T14:00:00.000Z"),
    content: "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps.",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a886043fbf51082518f0326"),
    patientId: 4,
    date: ISODate("2026-08-21T14:00:00.000Z"),
    content: "Le patient déclare avoir commencé à fumer depuis peu.\nHémoglobine A1C supérieure au niveau recommandé.",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a88604dfbf51082518f0328"),
    patientId: 4,
    date: ISODate("2026-08-21T14:00:00.000Z"),
    content: "Taille, Poids, Cholestérol, Vertige et Réaction.",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a886ba334f3594b93818175"),
    patientId: 1,
    date: ISODate("2026-08-21T15:15:47.123Z"),
    content: "test",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a886bab34f3594b93818176"),
    patientId: 1,
    date: ISODate("2026-08-21T15:15:55.116Z"),
    content: "test 2\r\ntest3",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a886dcbb011ae6e5a6f03d7"),
    patientId: 1,
    date: ISODate("2026-08-21T15:24:59.648Z"),
    content: "test 2\r\ntest3",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a89d276a7bed2828e60ce67"),
    patientId: 1,
    date: ISODate("2026-08-22T16:46:46.280Z"),
    content: "test\r\ntest\r\ntest",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a89d289a7bed2828e60ce68"),
    patientId: 1,
    date: ISODate("2026-08-22T16:47:05.532Z"),
    content: "test",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a89d28aa7bed2828e60ce69"),
    patientId: 1,
    date: ISODate("2026-08-22T16:47:06.776Z"),
    content: "test",
    _class: "medilabo.solutions.notes.model.Note"
  },
  {
    _id: ObjectId("6a89d630a7bed2828e60ce6a"),
    patientId: 1,
    date: ISODate("2026-08-22T17:02:40.319Z"),
    content: "nouvelle note",
    _class: "medilabo.solutions.notes.model.Note"
  }
]);
