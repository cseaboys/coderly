const mongoose = require("mongoose");

mongoose
  .connect("mongodb://localhost:27017")
  .then(async () => {
    console.log("Connected to MongoDB");

    const noteSchema = new mongoose.Schema({
      title: String,
      content: String,
    });

    const Note = mongoose.model("Note", noteSchema);

    // Create
    const note = await Note.create({
      title: "First Note",
      content: "This is the content of my first note.",
    });
    console.log("Created:", note);

    // Read
    const notes = await Note.find();
    console.log("All Notes:", notes);

    // Update
    const updatedNote = await Note.findByIdAndUpdate(
      note._id,
      { content: "Updated content" },
      { new: true },
    );
    console.log("Updated:", updatedNote);

    // Delete
    const deletedNote = await Note.findByIdAndDelete(note._id);
    console.log("Deleted:", deletedNote);
  })
  .catch((err) => console.error("Error:", err))
  .finally(() => {
    mongoose.connection.close();
  });
