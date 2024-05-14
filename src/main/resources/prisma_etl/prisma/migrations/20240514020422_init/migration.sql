/*
  Warnings:

  - You are about to drop the column `question` on the `Question` table. All the data in the column will be lost.
  - A unique constraint covering the columns `[label]` on the table `Question` will be added. If there are existing duplicate values, this will fail.
  - Added the required column `label` to the `Question` table without a default value. This is not possible if the table is not empty.

*/
-- DropIndex
DROP INDEX "Question_question_key";

-- AlterTable
ALTER TABLE "Question" DROP COLUMN "question",
ADD COLUMN     "label" TEXT NOT NULL;

-- CreateIndex
CREATE UNIQUE INDEX "Question_label_key" ON "Question"("label");
