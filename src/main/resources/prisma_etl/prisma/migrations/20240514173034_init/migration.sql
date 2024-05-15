/*
  Warnings:

  - Added the required column `option` to the `answer` table without a default value. This is not possible if the table is not empty.
  - Added the required column `sequence` to the `question` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "answer" ADD COLUMN     "option" BOOLEAN NOT NULL;

-- AlterTable
ALTER TABLE "question" ADD COLUMN     "sequence" INTEGER NOT NULL;
