#!/bin/sh
######## KTLINT-GRADLE HOOK START ########

CHANGED_FILES="$(git --no-pager diff --name-status --no-color --cached | awk '$1 != "D" && $NF ~ /\.kts?$/ { print $NF }')"

if [ -z "$CHANGED_FILES" ]; then
  echo "No Kotlin staged files."
  exit 0
fi

echo "Running ktlintFormat over these files:"
echo "$CHANGED_FILES"

diff=.git/unstaged-ktlint-git-hook.diff
git diff --color=never >$diff
if [ -s $diff ]; then
  git apply -R $diff
fi

./gradlew --quiet ktlintFormat -PinternalKtlintGitFilter="$CHANGED_FILES"
gradle_command_exit_code=$?

echo "Completed ktlintFormat run."

echo "$CHANGED_FILES" | while read -r file; do
  if [ -f $file ]; then
    git add $file
  fi
done

if [ -s $diff ]; then
  git apply --ignore-whitespace $diff
fi
rm $diff
unset diff

echo "Completed ktlintFormat hook."
if [ $gradle_command_exit_code -ne 0 ]; then
  exit $gradle_command_exit_code
fi
######## KTLINT-GRADLE HOOK END ########

######## KTLINT-GRADLE HOOK START ########

CHANGED_FILES="$(git --no-pager diff --name-status --no-color --cached | awk '$1 != "D" && $NF ~ /\.kts?$/ { print $NF }')"

if [ -z "$CHANGED_FILES" ]; then
  echo "No Kotlin staged files."
  exit 0
fi

echo "Running ktlintCheck over these files:"
echo "$CHANGED_FILES"

diff=.git/unstaged-ktlint-git-hook.diff
git diff --color=never >$diff
if [ -s $diff ]; then
  git apply -R $diff
fi

./gradlew --quiet ktlintCheck -PinternalKtlintGitFilter="$CHANGED_FILES"
gradle_command_exit_code=$?

echo "Completed ktlintCheck run."

if [ -s $diff ]; then
  git apply --ignore-whitespace $diff
fi
rm $diff
unset diff

echo "Completed ktlintCheck hook."
if [ $gradle_command_exit_code -ne 0 ]; then
  exit $gradle_command_exit_code
fi
######## KTLINT-GRADLE HOOK END ########

echo "Running detekt check..."
./gradlew detekt
