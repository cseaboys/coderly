db.engineers.drop();
use("engineers");
db.createCollection("engineers");

db.engineers.insertOne({ name: "Jane Doe", age: 21, gender: "Female" });
//insert
db.engineers.insertMany([
  { name: "Foo Bar", age: 25, gender: "Male" },
  { name: "Baz Qux", age: 27, gender: "Female" },
  { name: "A", age: 22, gender: "Female" },
  { name: "B", age: 24, gender: "Male" },
  { name: "C", age: 24, gender: "Female" },
]);

db.engineers.find({ name: "Foo Bar" });

db.engineers.find({ age: { $gte: 25 } });
db.engineers.find({ age: { $gt: 25 } });
db.engineers.find({ age: { $lt: 25 } });
db.engineers.find({ age: { $lte: 25 } });
db.engineers.find({ age: { $eq: 25 } });
db.engineers.find({ age: { $ne: 25 } });

db.engineers.find({
  $or: [{ age: { $gte: 25 } }, { gender: { $eq: "Male" } }],
});

db.engineers.find({
  $and: [
    {
      $or: [{ age: { $gte: 25 } }, { gender: { $ne: "Male" } }],
    },
    {
      $or: [{ gender: { $eq: "Female" } }, { age: { $gte: 18 } }],
    },
  ],
});

db.engineers.find({});

db.engineers.find({}, { gender: "Male" });

db.engineers.find({
  $nor: [{ age: { $eq: 25 } }, { gender: { $eq: "Male" } }],
});

db.engineers.updateOne(
  { name: "Foo Bar" },
  { $set: { name: "John Doe", age: 100 } }
);

db.engineers.find({ name: "John Doe" });

db.engineers.update(
  { age: { $gte: 25 } },
  { $set: { age: 26 } },
  { multi: true }
);

db.engineers.find({ age: 26 });

db.engineers.update(
  { name: "E" },
  {
    $set: {
      age: 26,
      gender: "Female",
    },
  },
  { upsert: true }
);
db.engineers.find({ age: 26 });


db.engineers.deleteOne({age: 26});
db.engineers.find({ age: 26 });

db.engineers.find({age:24})
db.engineers.deleteMany({age: 24});

// db.createCollection("Sample_with_save")
// db.getMongo().getDBs();
// use('Sample_with_save')
// db.getCollectionNames()
// db.Sample_with_save

db.engineers.replaceOne({age: 21},{age:31});

db.engineers.find()
