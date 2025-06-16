//renderização dos exercicios do usuario
export default function UserExercises({ user }) {
  return (
    <div className="user-exercises">
      <p>
        <strong>Meditações realizadas:</strong>{" "}
        {user.meditation_user?.length || 0}
      </p>
      <p>
        <strong>Respirações realizadas:</strong> {user.breath_user?.length || 0}
      </p>
    </div>
  );
}
