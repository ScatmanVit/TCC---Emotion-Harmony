/*
  Warnings:

  - You are about to drop the column `sleep_feeling` on the `tb_habits` table. All the data in the column will be lost.
  - You are about to drop the column `sleep_time_duration` on the `tb_habits` table. All the data in the column will be lost.
  - You are about to drop the column `sleep_time_end` on the `tb_habits` table. All the data in the column will be lost.
  - You are about to drop the column `sleep_time_start` on the `tb_habits` table. All the data in the column will be lost.
  - You are about to drop the column `value_habit` on the `tb_habits` table. All the data in the column will be lost.
  - You are about to alter the column `name_habit` on the `tb_habits` table. The data in that column could be lost. The data in that column will be cast from `Enum(EnumId(0))` to `VarChar(50)`.
  - You are about to drop the column `exercise_habit_user` on the `tb_users` table. All the data in the column will be lost.
  - You are about to drop the column `hydration_habit_user` on the `tb_users` table. All the data in the column will be lost.
  - You are about to drop the column `sleep_habit_user` on the `tb_users` table. All the data in the column will be lost.
  - You are about to drop the column `smoke_habit_user` on the `tb_users` table. All the data in the column will be lost.

*/
-- AlterTable
ALTER TABLE `tb_habits` DROP COLUMN `sleep_feeling`,
    DROP COLUMN `sleep_time_duration`,
    DROP COLUMN `sleep_time_end`,
    DROP COLUMN `sleep_time_start`,
    DROP COLUMN `value_habit`,
    ADD COLUMN `last_time_habit` DATETIME(3) NULL,
    MODIFY `name_habit` VARCHAR(50) NOT NULL;

-- AlterTable
ALTER TABLE `tb_users` DROP COLUMN `exercise_habit_user`,
    DROP COLUMN `hydration_habit_user`,
    DROP COLUMN `sleep_habit_user`,
    DROP COLUMN `smoke_habit_user`,
    ADD COLUMN `sleep_time_duration` INTEGER NULL,
    ADD COLUMN `sleep_time_end` DATETIME(3) NULL,
    ADD COLUMN `sleep_time_start` DATETIME(3) NULL;
