const { prisma } = require("../../../config/prisma");
const { verifyEmail, verifyCPF } = require("../../../utils/validation");
const CustomError = require("../../../core/errors/CustomError");
const bcrypt = require("bcrypt");

module.exports.updateUserService = async (id_user, body) => {
  console.log({ body });

  const { nome, email, cpf, senha, telefones } = body;
  console.log({ id_user, nome, email, cpf, senha, telefones });

  const user = await prisma.tb_users.findUnique({ where: { id_user } });
  if (!user) throw new CustomError("Usuário não encontrado", 404);

  const data = {};
  if (nome && nome !== user.name_user) data.name_user = nome;
  if (email && email !== user.email_user) {
    if (!verifyEmail(email)) throw new CustomError("Email inválido", 400);
    data.email_user = email;
  }
  if (cpf && cpf !== user.cpf_user) {
    if (!verifyCPF(cpf)) throw new CustomError("CPF inválido", 400);
    data.cpf_user = cpf.replace(/\D/g, "");
  }
  if (senha) data.password_user = await bcrypt.hash(senha, 10);

  telefones.forEach(async (telefone) => {
    await prisma.tb_phone.update({
      where: { id_phone: Number(telefone.id_phone) },
      data: {
        country_code_phone: 55,
        area_code_phone: Number(
          telefone.telefone.replace(/\D/g, "").slice(0, 2)
        ),
        phone_number: Number(telefone.telefone.replace(/\D/g, "").slice(2)),
      },
    });
  });

  return await prisma.tb_users.update({ where: { id_user }, data });
};
