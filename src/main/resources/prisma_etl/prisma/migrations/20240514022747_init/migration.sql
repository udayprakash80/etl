/*
  Warnings:

  - You are about to drop the column `questionId` on the `answer` table. All the data in the column will be lost.
  - Added the required column `question_id` to the `answer` table without a default value. This is not possible if the table is not empty.

*/
-- DropForeignKey
ALTER TABLE "answer" DROP CONSTRAINT "answer_questionId_fkey";

-- AlterTable
ALTER TABLE "answer" DROP COLUMN "questionId",
ADD COLUMN     "question_id" INTEGER NOT NULL;

-- AddForeignKey
ALTER TABLE "answer" ADD CONSTRAINT "answer_question_id_fkey" FOREIGN KEY ("question_id") REFERENCES "question"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
